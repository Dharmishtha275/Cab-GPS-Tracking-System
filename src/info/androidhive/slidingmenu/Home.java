package info.androidhive.slidingmenu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class Home extends Activity implements AnimationListener {
	ImageView imgLogo;
	Animation animRotate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		getActionBar().setTitle("Driver Cab App");
		ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#79BAEC")); 
		getActionBar().setBackgroundDrawable(colorDrawable);
		imgLogo = (ImageView) findViewById(R.id.imgLogo);
		
		
		// load the animation
				animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
						R.layout.rotate);

				// set animation listener
				animRotate.setAnimationListener(this);
				imgLogo.startAnimation(animRotate);

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		if (animation == animRotate) {
			
			Intent i=new Intent(getApplicationContext(),Login.class);
			//i.putExtra("name", edt_1.getText().toString());
			startActivity(i);
			finish();
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	

}
