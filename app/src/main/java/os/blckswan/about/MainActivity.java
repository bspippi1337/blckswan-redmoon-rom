package os.blckswan.about;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.*;
import android.graphics.drawable.GradientDrawable;

public class MainActivity extends Activity {
  TextView tv(String s, int sp, int color, int style) {
    TextView v = new TextView(this);
    v.setText(s);
    v.setTextSize(sp);
    v.setTextColor(color);
    v.setTypeface(Typeface.MONOSPACE, style);
    v.setPadding(24, 10, 24, 10);
    return v;
  }
  String prop(String key, String fallback) {
    try {
      Class<?> c = Class.forName("android.os.SystemProperties");
      return String.valueOf(c.getMethod("get", String.class, String.class).invoke(null, key, fallback));
    } catch (Throwable t) { return fallback; }
  }
  public void onCreate(Bundle b) {
    super.onCreate(b);
    int bg=Color.rgb(6,2,2), red=Color.rgb(255,54,54), green=Color.rgb(0,255,136), soft=Color.rgb(220,150,150), white=Color.rgb(255,238,238);
    ScrollView scroll=new ScrollView(this);
    LinearLayout root=new LinearLayout(this);
    root.setOrientation(LinearLayout.VERTICAL);
    root.setPadding(26,44,26,44);
    root.setBackgroundColor(bg);
    scroll.addView(root);
    TextView logo=tv("BLCKSWAN",42,red,Typeface.BOLD);
    logo.setGravity(Gravity.CENTER);
    root.addView(logo);
    TextView sub=tv("OS 42 · RESTLESS · RED MOON",16,white,Typeface.BOLD);
    sub.setGravity(Gravity.CENTER);
    root.addView(sub);
    GradientDrawable cardBg=new GradientDrawable();
    cardBg.setColor(Color.rgb(18,5,5));
    cardBg.setStroke(2,Color.rgb(150,20,20));
    cardBg.setCornerRadius(24);
    LinearLayout card=new LinearLayout(this);
    card.setOrientation(LinearLayout.VERTICAL);
    card.setPadding(24,24,24,24);
    card.setBackground(cardBg);
    LinearLayout.LayoutParams cp=new LinearLayout.LayoutParams(-1,-2);
    cp.setMargins(0,34,0,24);
    root.addView(card,cp);
    card.addView(tv("Device",13,soft,Typeface.BOLD));
    card.addView(tv(prop("ro.product.model",Build.MODEL),22,white,Typeface.BOLD));
    card.addView(tv("Edition",13,soft,Typeface.BOLD));
    card.addView(tv(prop("ro.blckswan.edition","RED MOON"),22,red,Typeface.BOLD));
    card.addView(tv("Build",13,soft,Typeface.BOLD));
    card.addView(tv(prop("ro.build.display.id",Build.DISPLAY),14,white,Typeface.NORMAL));
    card.addView(tv("Android",13,soft,Typeface.BOLD));
    card.addView(tv(Build.VERSION.RELEASE+" / SDK "+Build.VERSION.SDK_INT,18,white,Typeface.BOLD));
    root.addView(tv("> BLCKSWAN control plane online",16,green,Typeface.BOLD));
    root.addView(tv("> RED MOON edition active",16,red,Typeface.BOLD));
    root.addView(tv("> Motorola About bypassed",16,green,Typeface.BOLD));
    root.addView(tv("No Hello Moto. Red Moon rises.",15,soft,Typeface.BOLD));
    setContentView(scroll);
  }
}
