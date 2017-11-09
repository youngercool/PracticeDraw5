package com.hencoder.hencoderpracticedraw5.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class Practice03OnDrawLayout extends LinearLayout {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Pattern pattern = new Pattern();

    public Practice03OnDrawLayout(Context context) {
        super(context);
    }

    public Practice03OnDrawLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice03OnDrawLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 在这里插入 setWillNotDraw(false) 以启用完整的绘制流程
        setWillNotDraw(false);
    }
/**
* ViewGroup默认情况下，出于性能考虑，会被设置成WILL_NOT_DROW，这样，ondraw就不会被执行了。

 如果我们想重写一个viewgroup的ondraw方法，有两种方法：

 1，构造函数中，给viewgroup设置一个颜色。

 2，构造函数中，调用setWillNotDraw（false），去掉其WILL_NOT_DRAW flag。

 在viewgroup初始化的时候，它调用了一个私有方法：initViewGroup，
 它里面会有一句setFlags（WILLL_NOT_DRAW,DRAW_MASK）;相当于调用了setWillNotDraw（true），
 所以说，对于ViewGroup，他就认为是透明的了，如果我们想要重写onDraw，就要调用setWillNotDraw（false）。

*
* */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        pattern.draw(canvas);
    }

    private class Pattern {
        private static final float PATTERN_RATIO = 5f / 6;

        Paint patternPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Practice03OnDrawLayout.Pattern.Spot[] spots;

        private Pattern() {
            spots = new Practice03OnDrawLayout.Pattern.Spot[4];
            spots[0] = new Practice03OnDrawLayout.Pattern.Spot(0.24f, 0.3f, 0.026f);
            spots[1] = new Practice03OnDrawLayout.Pattern.Spot(0.69f, 0.25f, 0.067f);
            spots[2] = new Practice03OnDrawLayout.Pattern.Spot(0.32f, 0.6f, 0.067f);
            spots[3] = new Practice03OnDrawLayout.Pattern.Spot(0.62f, 0.78f, 0.083f);
        }

        private Pattern(Practice03OnDrawLayout.Pattern.Spot[] spots) {
            this.spots = spots;
        }

        {
            patternPaint.setColor(Color.parseColor("#A0E91E63"));
        }

        private void draw(Canvas canvas) {
            int repitition = (int) Math.ceil((float) getWidth() / getHeight());
            for (int i = 0; i < spots.length * repitition; i++) {
                Practice03OnDrawLayout.Pattern.Spot spot = spots[i % spots.length];
                canvas.drawCircle(i / spots.length * getHeight() * PATTERN_RATIO + spot.relativeX * getHeight(), spot.relativeY * getHeight(), spot.relativeSize * getHeight(), patternPaint);
            }
        }

        private class Spot {
            private float relativeX;
            private float relativeY;
            private float relativeSize;

            private Spot(float relativeX, float relativeY, float relativeSize) {
                this.relativeX = relativeX;
                this.relativeY = relativeY;
                this.relativeSize = relativeSize;
            }
        }
    }
}
