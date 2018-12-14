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

    private AppCompatTextView mTitle0, mTitle1, mTitle2, mTitle3, mTitle4, mTitle5, mTitle6, mTitle7;
    private AppCompatTextView mValue0, mValue1, mValue2, mValue3, mValue4, mValue5, mValue6, mValue7;
    private SeekBar mSeekBar0, mSeekBar1, mSeekBar2, mSeekBar3, mSeekBar4, mSeekBar5, mSeekBar6, mSeekBar7;

    private CharSequence mTitleText0, mTitleText1, mTitleText2, mTitleText3, mTitleText4, mTitleText5, mTitleText6, mTitleText7;

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

        mTitle0 = view.findViewById(R.id.title0);
        mValue0 = view.findViewById(R.id.value0);
        mSeekBar0 = view.findViewById(R.id.seekbar0);

        mTitle1 = view.findViewById(R.id.title1);
        mValue1 = view.findViewById(R.id.value1);
        mSeekBar1 = view.findViewById(R.id.seekbar1);

        mTitle2 = view.findViewById(R.id.title2);
        mValue2 = view.findViewById(R.id.value2);
        mSeekBar2 = view.findViewById(R.id.seekbar2);

        mTitle3 = view.findViewById(R.id.title3);
        mValue3 = view.findViewById(R.id.value3);
        mSeekBar3 = view.findViewById(R.id.seekbar3);

        mTitle4 = view.findViewById(R.id.title4);
        mValue4 = view.findViewById(R.id.value4);
        mSeekBar4 = view.findViewById(R.id.seekbar4);

        mTitle5 = view.findViewById(R.id.title5);
        mValue5 = view.findViewById(R.id.value5);
        mSeekBar5 = view.findViewById(R.id.seekbar5);

        mTitle6 = view.findViewById(R.id.title6);
        mValue6 = view.findViewById(R.id.value6);
        mSeekBar6 = view.findViewById(R.id.seekbar6);

        mTitle7 = view.findViewById(R.id.title7);
        mValue7 = view.findViewById(R.id.value7);
        mSeekBar7 = view.findViewById(R.id.seekbar7);


        mSeekBar0.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                if (value < mItems.size() && value >= 0) {
                    mProgress = value;
                    String text = mItems.get(value);
                    if (mUnit != null) text += mUnit;
                    mValue0.setText(text);
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
        mSeekBar0.setFocusable(false);

        mSeekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                if (value < mItems.size() && value >= 0) {
                    mProgress = value;
                    String text = mItems.get(value);
                    if (mUnit != null) text += mUnit;
                    mValue1.setText(text);
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
        mSeekBar1.setFocusable(false);

        super.onCreateView(view);
    }

    public void setTitle0(CharSequence title) {
        mTitleText0 = title;
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
        if (mTitle0 != null && mTitle1 != null && mTitle2 != null && mTitle3 != null
                && mTitle4 != null && mTitle5 != null && mTitle6 != null && mTitle7 != null) {
            if (mTitleText0 != null && mTitleText1 != null && mTitleText2 != null && mTitleText3 != null
                    && mTitleText4 != null && mTitleText5 != null && mTitleText6 != null && mTitleText7 != null) {
                mTitle0.setText(mTitleText0);
                mTitle0.setVisibility(View.VISIBLE);
                mTitle1.setText(mTitleText1);
                mTitle1.setVisibility(View.VISIBLE);
                mTitle2.setText(mTitleText2);
                mTitle2.setVisibility(View.VISIBLE);
                mTitle3.setText(mTitleText3);
                mTitle3.setVisibility(View.VISIBLE);
                mTitle4.setText(mTitleText4);
                mTitle4.setVisibility(View.VISIBLE);
                mTitle5.setText(mTitleText5);
                mTitle5.setVisibility(View.VISIBLE);
                mTitle6.setText(mTitleText6);
                mTitle6.setVisibility(View.VISIBLE);
                mTitle7.setText(mTitleText7);
                mTitle7.setVisibility(View.VISIBLE);
            } else {
                mTitle0.setVisibility(View.GONE);
                mTitle1.setVisibility(View.GONE);
                mTitle2.setVisibility(View.GONE);
                mTitle3.setVisibility(View.GONE);
                mTitle4.setVisibility(View.GONE);
                mTitle5.setVisibility(View.GONE);
                mTitle6.setVisibility(View.GONE);
                mTitle7.setVisibility(View.GONE);
            }
        }
        if (mItems.size() == 0) {
            for (int i = mMin; i <= mMax; i += mOffset) {
                mItems.add(String.valueOf(i));
            }
        }
        if (mSeekBar0 != null) {
            mSeekBar0.setMax(mItems.size() - 1);
            //mSeekBar.setMin(0);
            mSeekBar0.setAlpha(mAlpha);
            mSeekBar0.setEnabled(mEnabled);
            if (mValue0 != null) {
                try {
                    String text = mItems.get(mProgress);
                    mSeekBar0.setProgress(mProgress);
                    if (mUnit != null) text += mUnit;
                    mValue0.setText(text);
                } catch (Exception ignored) {
                    mValue0.setText(mValue0.getResources().getString(R.string.not_in_range));
                }
            }
            if(mEnabled){
                mSeekBar0.setAlpha(1f);
            } else {
                mSeekBar0.setAlpha(0.4f);
            }
        }
    }
}
