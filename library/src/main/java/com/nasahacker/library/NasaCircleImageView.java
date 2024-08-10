package com.nasahacker.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

/**
 * CodeWithTamim
 *
 * @developer Tamim Hossain
 * @mail tamimh.dev@gmail.com
 */
public class NasaCircleImageView extends AppCompatImageView
{

    private Paint paint;
    private Path path;
    private int borderColor;
    private float borderWidth;

    public NasaCircleImageView(Context context)
    {
        super(context);
        init(null);
    }

    public NasaCircleImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs);
    }

    public NasaCircleImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs)
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        path = new Path();

        if (attrs != null)
        {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.NasaCircleImageView,
                    0, 0);

            try
            {
                borderColor = typedArray.getColor(R.styleable.NasaCircleImageView_borderColor, Color.TRANSPARENT);
                borderWidth = typedArray.getDimension(R.styleable.NasaCircleImageView_borderWidth, 0);
            } finally
            {
                typedArray.recycle();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int width = getWidth();
        int height = getHeight();
        float radius = Math.min(width, height) / 2f;
        float cx = width / 2f;
        float cy = height / 2f;
        path.addCircle(cx, cy, radius, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
        if (borderWidth > 0 && borderColor != Color.TRANSPARENT)
        {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(borderWidth);
            paint.setColor(borderColor);
            canvas.drawCircle(cx, cy, radius - borderWidth / 2, paint);
        }
    }

    public void setBorderColor(int color) {
        this.borderColor = color;
        invalidate();
    }
    public void setBorderColorResource(int colorResId) {
        this.borderColor = ContextCompat.getColor(getContext(), colorResId);
        invalidate();
    }

    public void setBorderWidth(float width)
    {
        borderWidth = width;
        invalidate();
    }
}
