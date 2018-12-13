package com.moro.mtweaks.views.recyclerview;

import android.view.View;
import android.widget.SeekBar;

import com.moro.mtweaks.R;
import com.moro.mtweaks.utils.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by willi on 06.05.16.
 */
public class EqualizerView extends RecyclerViewItem {

    public interface OnSeekBarListener {
        void onStop(EqualizerView seekBarView, int position, String value);

        void onMove(EqualizerView seekBarView, int position, String value);
    }

    private AppCompatTextView mTitle;
    private AppCompatTextView mValue;
    private SeekBar mSeekBar;

    private CharSequence mTitleText;

    private int mMin;
    private int mMax = 100;
    private int mProgress;
    private String mUnit;
    private List<String> mItems = new ArrayList<>();
    private int mOffset = 1;
    private boolean mEnabled = true;
    private float mAlpha = 1f;

    private OnSeekBarListener mOnSeekBarListener;

    @Override
    public int getLayoutRes() {
        return R.layout.rv_equalizer_view;
    }

    @Override
    public void onCreateView(final View view) {
        mTitle = view.findViewById(R.id.title1);
        mValue = view.findViewById(R.id.value1);
        mSeekBar = view.findViewById(R.id.seekbar1);


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                if (value < mItems.size() && value >= 0) {
                    mProgress = value;
                    String text = mItems.get(value);
                    if (mUnit != null) text += mUnit;
                    mValue.setText(text);
                    if (mOnSeekBarListener != null) {
                        mOnSeekBarListener.onMove(
                                EqualizerView.this, mProgress, mItems.get(mProgress));
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                try {
                    if (mOnSeekBarListener != null) {
                        mOnSeekBarListener.onStop(
                                EqualizerView.this, mProgress, mItems.get(mProgress));
                    }
                } catch (Exception e) {
                    Log.crashlyticsE(e.getMessage());
                }
            }
        });
        mSeekBar.setFocusable(false);

        super.onCreateView(view);
    }

    public void setTitle(CharSequence title) {
        mTitleText = title;
        refresh();
    }

    public void setProgress(int progress) {
        mProgress = progress;
        refresh();
    }

    public void setMin(int min) {
        mMin = min;
        mItems.clear();
        refresh();
    }

    public void setUnit(String unit) {
        mUnit = unit;
        mItems.clear();
        refresh();
    }

    public void setMax(int max) {
        mMax = max;
        mItems.clear();
        refresh();
    }

    public void setItems(List<String> items) {
        mItems.clear();
        mItems.addAll(items);
        refresh();
    }

    public void setOffset(int offset) {
        mOffset = offset;
        mItems.clear();
        refresh();
    }

    public void setEnabled(boolean enable) {
        mEnabled = enable;
        refresh();
    }

    public void setAlpha(float alpha) {
        mAlpha = alpha;
        refresh();
    }

    public int getProgress() {
        return mProgress;
    }

    public void setOnSeekBarListener(OnSeekBarListener onSeekBarListener) {
        mOnSeekBarListener = onSeekBarListener;
    }

    @Override
    protected void refresh() {
        super.refresh();
        if (mTitle != null) {
            if (mTitleText != null) {
                mTitle.setText(mTitleText);
                mTitle.setVisibility(View.VISIBLE);
            } else {
                mTitle.setVisibility(View.GONE);
            }
        }
        if (mItems.size() == 0) {
            for (int i = mMin; i <= mMax; i += mOffset) {
                mItems.add(String.valueOf(i));
            }
        }
        if (mSeekBar != null) {
            mSeekBar.setMax(mItems.size() - 1);
            //mSeekBar.setMin(0);
            mSeekBar.setAlpha(mAlpha);
            mSeekBar.setEnabled(mEnabled);
            if (mValue != null) {
                try {
                    String text = mItems.get(mProgress);
                    mSeekBar.setProgress(mProgress);
                    if (mUnit != null) text += mUnit;
                    mValue.setText(text);
                } catch (Exception ignored) {
                    mValue.setText(mValue.getResources().getString(R.string.not_in_range));
                }
            }
            if(mEnabled){
                mSeekBar.setAlpha(1f);
            } else {
                mSeekBar.setAlpha(0.4f);
            }
        }
    }
}
