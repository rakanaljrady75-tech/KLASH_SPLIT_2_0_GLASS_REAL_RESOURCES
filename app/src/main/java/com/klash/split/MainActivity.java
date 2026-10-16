package com.klash.split;

import android.app.*;import android.os.*;import android.content.*;import android.graphics.*;import android.graphics.drawable.*;
import android.net.Uri;import android.provider.MediaStore;import android.view.*;import android.widget.*;import java.io.*;import java.util.*;

public class MainActivity extends Activity {
 LinearLayout root,body; ImageView preview; TextView status; Bitmap src; int cols=3,rows=1;
 float zoom=1,dx=0,dy=0,lx,ly; android.media.SoundPool sp; int click,ok;
 String[] greetings={"كلاش يرحّب بك ✦","نورت KLASH SPLIT 🔥","جاهز نخلّي بروفايلك يفرق؟ ⚡","ابدأ الإبداع يا ذيب 🎬","كلاش معك، والباقي علينا ✨"};

 public void onCreate(Bundle b){super.onCreate(b); getWindow().setStatusBarColor(Color.rgb(8,7,20));getWindow().setNavigationBarColor(Color.rgb(8,7,20));
  sp=new android.media.SoundPool.Builder().setMaxStreams(3).build();click=sp.load(this,R.raw.click,1);ok=sp.load(this,R.raw.success,1);ui();welcome();}
 void s(){sp.play(click,.5f,.5f,1,0,1);}
 TextView t(String x,int z){TextView v=new TextView(this);v.setText(x);v.setTextColor(Color.WHITE);v.setTextSize(z);v.setGravity(17);v.setPadding(10,8,10,8);return v;}
 GradientDrawable g(int c,int r){GradientDrawable q=new GradientDrawable();q.setColor(c);q.setCornerRadius(r);q.setStroke(1,Color.argb(35,255,255,255));return q;}
 Button b(String x){Button q=new Button(this);q.setText(x);q.setTextColor(Color.WHITE);q.setTextSize(13);q.setAllCaps(false);q.setBackground(g(Color.rgb(30,25,49),22));q.setOnClickListener(v->s());return q;}

 void ui(){
  root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(16,12,16,10);root.setBackgroundColor(Color.rgb(8,7,20));
  LinearLayout top=new LinearLayout(this);TextView logo=t("KLASH SPLIT",24);logo.setTypeface(null,1);top.addView(logo,new LinearLayout.LayoutParams(0,65,1));
  TextView badge=t("OFFLINE  •  2.0",11);badge.setBackground(g(Color.rgb(47,28,70),30));top.addView(badge,new LinearLayout.LayoutParams(135,44));root.addView(top);
  TextView info=t("استوديو تقسيم أغلفة TikTok",14);root.addView(info,new LinearLayout.LayoutParams(-1,42));
  preview=new ImageView(this);preview.setScaleType(ImageView.ScaleType.MATRIX);preview.setBackground(g(Color.rgb(18,17,30),26));root.addView(preview,new LinearLayout.LayoutParams(-1,0,1));
  status=t("اختَر صورة للبدء",12);root.addView(status,new LinearLayout.LayoutParams(-1,42));
  LinearLayout r1=new LinearLayout(this);Button pick=b("＋ صورة");Button reset=b("↺ إعادة");r1.addView(pick,new LinearLayout.LayoutParams(0,54,1));r1.addView(reset,new LinearLayout.LayoutParams(0,54,1));root.addView(r1);
  LinearLayout r2=new LinearLayout(this);Button x3=b("1×3");Button x2=b("1×2");Button x4=b("1×4");r2.addView(x3,new LinearLayout.LayoutParams(0,54,1));r2.addView(x2,new LinearLayout.LayoutParams(0,54,1));r2.addView(x4,new LinearLayout.LayoutParams(0,54,1));root.addView(r2);
  LinearLayout r3=new LinearLayout(this);Button grid=b("3×3");Button grid2=b("2×3");Button grid4=b("4×4");r3.addView(grid,new LinearLayout.LayoutParams(0,54,1));r3.addView(grid2,new LinearLayout.LayoutParams(0,54,1));r3.addView(grid4,new LinearLayout.LayoutParams(0,54,1));root.addView(r3);
  LinearLayout r4=new LinearLayout(this);Button fit=b("أظهر الكل");Button fill=b("املأ");Button exp=b("تصدير");r4.addView(fit,new LinearLayout.LayoutParams(0,54,1));r4.addView(fill,new LinearLayout.LayoutParams(0,54,1));r4.addView(exp,new LinearLayout.LayoutParams(0,54,1));root.addView(r4);
  Button dev=b("💬 المطور كلاش  •  @YM_IBB");root.addView(dev,new LinearLayout.LayoutParams(-1,56));setContentView(root);
  pick.setOnClickListener(v->{s();pick();});reset.setOnClickListener(v->{zoom=1;dx=dy=0;apply();});
  x3.setOnClickListener(v->{cols=3;rows=1;status.setText("1×3 • ثلاثة أجزاء");});x2.setOnClickListener(v->{cols=2;rows=1;status.setText("1×2 • جزآن");});x4.setOnClickListener(v->{cols=4;rows=1;status.setText("1×4 • أربعة أجزاء");});
  grid.setOnClickListener(v->{cols=3;rows=3;status.setText("3×3 • تسعة أجزاء");});grid2.setOnClickListener(v->{cols=3;rows=2;status.setText("2×3 • ستة أجزاء");});grid4.setOnClickListener(v->{cols=4;rows=4;status.setText("4×4 • 16 جزء");});
  fit.setOnClickListener(v->{zoom=1;dx=dy=0;apply();});fill.setOnClickListener(v->{zoom=1.25f;apply();});
  exp.setOnClickListener(v->exportAll());dev.setOnClickListener(v->{s();try{startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://t.me/YM_IBB")));}catch(Exception e){}});
  preview.setOnTouchListener((v,e)->touch(e));
 }
 void welcome(){
  Dialog d=new Dialog(this);LinearLayout q=new LinearLayout(this);q.setOrientation(LinearLayout.VERTICAL);q.setPadding(25,28,25,24);q.setGravity(17);q.setBackground(g(Color.rgb(20,16,37),34));
  TextView a=t("KLASH",34);a.setTypeface(null,1);TextView m=t(greetings[new Random().nextInt(greetings.length)],21);
  TextView z=t("صمّم • قسّم • صدّر\nيعمل محليًا بدون إنترنت",13);Button go=b("دخول إلى التطبيق  ✦");
  q.addView(a,new LinearLayout.LayoutParams(-1,60));q.addView(m,new LinearLayout.LayoutParams(-1,105));q.addView(z,new LinearLayout.LayoutParams(-1,65));q.addView(go,new LinearLayout.LayoutParams(-1,56));
  d.setContentView(q);if(d.getWindow()!=null)d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));go.setOnClickListener(v->{s();d.dismiss();});d.show();
  if(d.getWindow()!=null)d.getWindow().setLayout((int)(getResources().getDisplayMetrics().widthPixels*.88),-2);
 }
 void pick(){Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT);i.setType("image/*");i.addCategory(Intent.CATEGORY_OPENABLE);startActivityForResult(i,8);}
 protected void onActivityResult(int r,int c,Intent d){super.onActivityResult(r,c,d);if(r==8&&c==RESULT_OK&&d!=null)try{src=MediaStore.Images.Media.getBitmap(getContentResolver(),d.getData());status.setText("تم تحميل الصورة • حرّكها أو كبّرها");apply();}catch(Exception e){status.setText("تعذر فتح الصورة");}}
 void apply(){if(src==null)return;int w=preview.getWidth(),h=preview.getHeight();if(w<1||h<1)return;float base=Math.max((float)w/src.getWidth(),(float)h/src.getHeight());Matrix m=new Matrix();m.setScale(base*zoom,base*zoom);m.postTranslate((w-src.getWidth()*base*zoom)/2+dx,(h-src.getHeight()*base*zoom)/2+dy);preview.setImageMatrix(m);}
 boolean touch(MotionEvent e){if(src==null)return true;if(e.getAction()==0){lx=e.getX();ly=e.getY();return true;}if(e.getAction()==2){dx+=e.getX()-lx;dy+=e.getY()-ly;lx=e.getX();ly=e.getY();apply();return true;}return true;}
 Bitmap crop(int i){int w=src.getWidth(),h=src.getHeight(),cw=w/cols,ch=h/rows,col=i%cols,row=i/cols;return Bitmap.createBitmap(src,col*cw,row*ch,col==cols-1?w-col*cw:cw,row==rows-1?h-row*ch:ch);}
 void exportAll(){if(src==null){Toast.makeText(this,"اختَر صورة أولاً",0).show();return;}File dir=new File(getExternalFilesDir(null),"KLASH_SPLIT");dir.mkdirs();int n=cols*rows;
  for(int i=0;i<n;i++)try{FileOutputStream o=new FileOutputStream(new File(dir,String.format(Locale.US,"KLASH_%02d.png",i+1)));crop(i).compress(Bitmap.CompressFormat.PNG,100,o);o.close();}catch(Exception e){}
  sp.play(ok,.7f,.7f,1,0,1);status.setText("تم التصدير • "+n+" أجزاء • KLASH");Toast.makeText(this,"تم حفظ "+n+" أجزاء",Toast.LENGTH_LONG).show();
 }
}