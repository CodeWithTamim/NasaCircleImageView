package com.nasahacker.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

/**
 * NasaCircleImageView is a custom ImageView designed to display images in a circular shape.
 * It includes optional border customization (color and width) and uses a BitmapShader to render
 * the circular shape efficiently.
 * <p>
 * Features:
 * - Circular display of images with anti-aliased borders
 * - Customizable border color and width
 * - Automatically crops image to fit circular view using CENTER_CROP scale type by default
 * </p>
 * Created by Tamim Hossain.
 */
public class NasaCircleImageView extends AppCompatImageView {

    private Paint paint;            // Paint for the image content
    private Paint borderPaint;      // Paint for the border
    private BitmapShader bitmapShader; // Shader to render image as a circle
    private Matrix shaderMatrix;    // Matrix to scale and position the shader
    private RectF drawableRect;     // Rect for drawable bounds
    private Path path;              // Path for clipping circle

    private int borderColor;        // Color of the border
    private float borderWidth;      // Width of the border in pixels

    /**
     * Constructor to initialize the view programmatically.
     *
     * @param context the context of the view
     */
    public NasaCircleImageView(Context context) {
        super(context);
        init(null);
    }

    /**
     * Constructor to initialize the view via XML attributes.
     *
     * @param context the context of the view
     * @param attrs   the XML attributes for customization
     */
    public NasaCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    /**
     * Constructor to initialize the view via XML attributes and style.
     *
     * @param context      the context of the view
     * @param attrs        the XML attributes for customization
     * @param defStyleAttr the default style attribute
     */
    public NasaCircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * Initializes the view's attributes, setting up default properties for paint and border.
     *
     * @param attrs the XML attributes for customization, if any
     */
    private void init(@Nullable AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);

        shaderMatrix = new Matrix();
        path = new Path();
        drawableRect = new RectF();

        if (attrs != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.NasaCircleImageView, 0, 0);
            try {
                borderColor = typedArray.getColor(R.styleable.NasaCircleImageView_borderColor, Color.TRANSPARENT);
                borderWidth = typedArray.getDimension(R.styleable.NasaCircleImageView_borderWidth, 0);
            } finally {
                typedArray.recycle();
            }
        }
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);
        setScaleType(ScaleType.CENTER_CROP);  // Default ScaleType for proper circular scaling
    }

    /**
     * Draws the circular image and border on the Canvas.
     *
     * @param canvas the Canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = getBitmapFromDrawable();
        if (bitmap == null) return;

        // Initialize the BitmapShader for circular rendering
        if (bitmapShader == null || !bitmap.equals(bitmapShader)) {
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        }
        paint.setShader(bitmapShader);

        int width = getWidth();
        int height = getHeight();
        float radius = Math.min(width, height) / 2f;
        float cx = width / 2f;
        float cy = height / 2f;

        // Update shader matrix for the correct scale and positioning
        updateShaderMatrix(bitmap, width, height);

        drawableRect.set(borderWidth, borderWidth, width - borderWidth, height - borderWidth);
        path.addCircle(cx, cy, radius - borderWidth, Path.Direction.CW);

        canvas.drawPath(path, paint);

        // Draw the border if specified
        if (borderWidth > 0) {
            canvas.drawCircle(cx, cy, radius - borderWidth / 2, borderPaint);
        }
    }

    /**
     * Retrieves the Bitmap from the current Drawable.
     *
     * @return the Bitmap representing the Drawable
     */
    private Bitmap getBitmapFromDrawable() {
        if (getDrawable() == null) return null;
        Bitmap bitmap = Bitmap.createBitmap(getDrawable().getIntrinsicWidth(),
                getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        getDrawable().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        getDrawable().draw(canvas);
        return bitmap;
    }

    /**
     * Updates the shader matrix to ensure the image scales and centers correctly.
     *
     * @param bitmap    the Bitmap to be drawn
     * @param viewWidth the width of the view
     * @param viewHeight the height of the view
     */
    private void updateShaderMatrix(Bitmap bitmap, int viewWidth, int viewHeight) {
        float scale;
        float dx = 0, dy = 0;

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        if (bitmapWidth * viewHeight > viewWidth * bitmapHeight) {
            scale = (float) viewHeight / (float) bitmapHeight;
            dx = (viewWidth - bitmapWidth * scale) * 0.5f;
        } else {
            scale = (float) viewWidth / (float) bitmapWidth;
            dy = (viewHeight - bitmapHeight * scale) * 0.5f;
        }

        shaderMatrix.setScale(scale, scale);
        shaderMatrix.postTranslate(dx, dy);
        bitmapShader.setLocalMatrix(shaderMatrix);
    }

    /**
     * Sets the color of the border.
     *
     * @param color the color to set for the border
     */
    public void setBorderColor(int color) {
        this.borderColor = color;
        borderPaint.setColor(color);
        invalidate();
    }

    /**
     * Sets the border color from a resource ID.
     *
     * @param colorResId the resource ID of the color to set for the border
     */
    public void setBorderColorResource(int colorResId) {
        this.borderColor = ContextCompat.getColor(getContext(), colorResId);
        borderPaint.setColor(borderColor);
        invalidate();
    }

    /**
     * Sets the width of the border.
     *
     * @param width the width of the border in pixels
     */
    public void setBorderWidth(float width) {
        borderWidth = width;
        borderPaint.setStrokeWidth(width);
        invalidate();
    }
}
