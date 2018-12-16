package com.moro.mtweaks.views.recyclerview;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.SeekBar;

import com.moro.mtweaks.R;
import com.moro.mtweaks.utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by morogoku on 14.12.18.
 */
public class EqualizerView extends RecyclerViewItem {

    public interface OnSeekBarListener {
        void onStop(EqualizerView equalizerView, int position, String value, int id);

        void onMove(EqualizerView equalizerView, int position, String value, int id);
    }

    private AppCompatTextView mTitle0, mTitle1, mTitle2, mTitle3, mTitle4, mTitle5, mTitle6, mTitle7;
    private AppCompatTextView mValue0, mValue1, mValue2, mValue3, mValue4, mValue5, mValue6, mValue7;
    private SeekBar mSeekBar0, mSeekBar1, mSeekBar2, mSeekBar3, mSeekBar4, mSeekBar5, mSeekBar6, mSeekBar7;

    private List<AppCompatTextView> mTitles = new ArrayList<>();
    private List<AppCompatTextView> mValues = new ArrayList<>();
    private List<SeekBar> mSeekBars = new ArrayList<>();

    private List<Integer> mProgress = new ArrayList<>();
    private String mUnit;
    private List<String> mItems = new ArrayList<>();
    private List<String> mTitlesText = new ArrayList<>();
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

        mTitles = Arrays.asList(mTitle0, mTitle1, mTitle2, mTitle3, mTitle4, mTitle5, mTitle6, mTitle7);
        mValues = Arrays.asList(mValue0, mValue1, mValue2, mValue3, mValue4, mValue5, mValue6, mValue7);
        mSeekBars = Arrays.asList(mSeekBar0, mSeekBar1, mSeekBar2, mSeekBar3, mSeekBar4, mSeekBar5, mSeekBar6, mSeekBar7);

        for (int i = 0; i < 8; i++) {
            int I = i;

            mSeekBars.get(i).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if (i < mItems.size() && i >= 0) {
                        mProgress.set(I, i);
                        String text = mItems.get(i);
                        if (mUnit != null) text += mUnit;
                        mValues.get(I).setText(text);
                        if (mOnSeekBarListener != null) {
                            mOnSeekBarListener.onMove(
                                    EqualizerView.this, mProgress.get(I), mItems.get(mProgress.get(I)), I);
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
                                    EqualizerView.this, mProgress.get(I), mItems.get(mProgress.get(I)), I);
                        }
                    } catch (Exception e) {
                        Log.crashlyticsE(e.getMessage());
                    }
                }
            });

            mSeekBars.get(i).setFocusable(false);
        }

        super.onCreateView(view);
    }

    public void setTitles(List<String> title) {
        mTitlesText.clear();
        mTitlesText.addAll(title);
        refresh();
    }

    public void setProgress(List<Integer> progress) {
        mProgress.clear();
        for (int i = 0; i < 8; i++) {
            mProgress.add(progress.get(i));
        }
        refresh();
    }

    public void setUnit(String unit) {
        mUnit = unit;
        mItems.clear();
        refresh();
    }

    public void setItems(List<String> items) {
        mItems.clear();
        mItems.addAll(items);
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

    public List<Integer> getProgress() {
        return mProgress;
    }

    public void setOnSeekBarListener(OnSeekBarListener onSeekBarListener) {
        mOnSeekBarListener = onSeekBarListener;
    }

    @Override
    protected void refresh() {
        super.refresh();
        if (mTitle0 != null && mTitle1 != null && mTitle2 != null && mTitle3 != null && mTitle4 != null
                && mTitle5 != null && mTitle6 != null && mTitle7 != null) {
            if (mTitlesText != null) {
                for (int i = 0; i < 8; i++) {
                    mTitles.get(i).setText(mTitlesText.get(i));
                    mTitles.get(i).setVisibility(View.VISIBLE);
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    mTitles.get(i).setVisibility(View.GONE);
                }
            }
        }
        if (mSeekBar0 != null && mSeekBar1 != null && mSeekBar2 != null && mSeekBar3 != null
                && mSeekBar4 != null && mSeekBar5 != null && mSeekBar6 != null && mSeekBar7 != null) {
            for (int i = 0; i < 8; i++) {
                mSeekBars.get(i).setMax(mItems.size() - 1);
                mSeekBars.get(i).setAlpha(mAlpha);
                mSeekBars.get(i).setEnabled(mEnabled);
                if (mValue0 != null && mValue1 != null && mValue2 != null && mValue3 != null
                        && mValue4 != null && mValue5 != null && mValue6 != null && mValue7 != null) {
                    try {
                        String text = mItems.get(mProgress.get(i));
                        mSeekBars.get(i).setProgress(mProgress.get(i));
                        if (mUnit != null) text += mUnit;
                        mValues.get(i).setText(text);
                    } catch (Exception ignored) {
                        mValues.get(i).setText(mValues.get(i).getResources().getString(R.string.not_in_range));
                    }
                }
                if (mEnabled) {
                    mSeekBars.get(i).setAlpha(1f);
                } else {
                    mSeekBars.get(i).setAlpha(0.4f);
                }
            }
        }
    }
}
