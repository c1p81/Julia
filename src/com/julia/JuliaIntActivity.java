package com.julia;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.widget.ImageView;


public class JuliaIntActivity extends Activity {
    /** Called when the activity is first created. */

	private ProgressDialog progDailog;
	public int[][] matrice = new int[320][240];
	public Paint paint;
	public Canvas canvas;
	public Bitmap bitmap;
	public ImageView view;


    
    public void onCreate(Bundle savedInstanceState) {
 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = (ImageView) findViewById(R.id.imageView1);
        Display display = getWindowManager().getDefaultDisplay();
        final int width = display.getWidth(); //240
        final int height = display.getHeight();
        bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
        
        paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas = new Canvas(bitmap);
         
        canvas.drawColor(Color.BLACK);
        view.setImageBitmap(bitmap);
        
        

        progDailog = ProgressDialog.show(this,"Sto calcolando", "Prego attendere....",true);
        new Thread() {
  
    		


        	public void run() {
          		double re_min = -2;
        		double re_max = 2.0;
        		double im_min = -2.0;
        		double im_max = 2.0;
        		int iterazioni = 100;
        		double ynew;
        		double xnew;
        		double a;
        		double b;
        		int test;
        		double re_factor;
        		double im_factor;
        		
        		re_factor = (re_max - re_min);
                im_factor = (im_max - im_min);
                a = -0.125;
                b = 0.75;
            try{

           //calcola il frattale

                for (int i=0;i<240;i++)
                {
                	for (int j=0;j<320;j++)
                	{
                		double x = im_min + i * im_factor / 240;
                	    double y = re_min + j * re_factor / 320;
                	    test = 0;
                		for (int k=0;k<iterazioni;k++)
                		{
                		      xnew = x * x - y * y + a;
                		      ynew = 2 * x * y + b;
                		      if (((xnew*xnew)+(ynew*ynew))>4)
                		      {
                		    	  test = k;
                		    	  k = iterazioni;
               		    	  
                		      }
                		      x = xnew;
                		      y = ynew;
                		}
                	
                		if (test%2 == 0)
                		{
                	        matrice[j][i]=1;

                		}
            	        canvas.drawPoint(i, j, paint);


                	}
                	
                }

        	} 
        	catch (Exception e) { }
        	
        	handler.sendEmptyMessage(0);
        	progDailog.dismiss(); }
        	}.start();
    }
    private Handler handler = new Handler() {
    	@Override
    	public void handleMessage(Message msg) {

    	}
    	};
    
}
