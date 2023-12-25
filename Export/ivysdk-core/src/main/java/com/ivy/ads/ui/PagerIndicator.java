package com.ivy.ads.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PagerIndicator extends View {
  private int count;
  private int selection;
  private int grey;
  private int white;
  private int r;

  private Paint circlePaint;

  public PagerIndicator(Context context) {
    super(context);
    init(context, null);
  }

  public PagerIndicator(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public PagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  public void setColors(int grey, int white) {
    this.grey = grey;
    this.white = white;
  }

  public void setCount(int count) {
    this.count = count;
    postInvalidate();
  }

  public void setSelection(int idx) {
    this.selection = idx;
    postInvalidate();
  }

  public void init(Context context, AttributeSet attrs) {
    this.circlePaint = new Paint();
    if (attrs != null) {
      grey = attrs.getAttributeIntValue(null, "grey", 0x88ffffff);
      white = attrs.getAttributeIntValue(null, "white", 0xffffffff);
      count = attrs.getAttributeIntValue(null, "count", 0);
      selection = attrs.getAttributeIntValue(null, "selection", 0);
      r = attrs.getAttributeIntValue(null, "radius", 4);
      r = (int) (context.getResources().getDisplayMetrics().density * r);
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    try {
      final int r = this.r;
      final int offset = (count % 2 == 0) ? r / 2 : 0;
      final int cx = getWidth() / 2 + offset;
      final int cy = getHeight() / 2;
      final int halfCount = count / 2;
      final Paint paint = circlePaint;

      for (int i = 0; i < count; ++i) {
        if (i == selection) {
          paint.setColor(white);
        } else {
          paint.setColor(grey);
        }
        if (i < halfCount) {
          canvas.drawCircle(cx - (halfCount - i) * 3 * r, cy, r, paint);
        } else {
          canvas.drawCircle(cx + (i - halfCount) * 3 * r, cy, r, paint);
        }
      }
    } catch(Throwable t) {
      // ignore
    }
  }
}
