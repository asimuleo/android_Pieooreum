package com.smu.team_andeu.adapters;

import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    @BindingAdapter("renderHtml")
    public static void bindRenderHtml(View view, String description){
        if(description != null) {
            ((TextView)view).setText(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT));
            ((TextView) view).setMovementMethod(LinkMovementMethod.getInstance());
        }else{
            ((TextView)view).setText("");
        }
    }

}
