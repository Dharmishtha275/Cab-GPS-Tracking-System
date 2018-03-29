package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class CustomerReqListActivity extends Activity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_customer_req);
	
	FragmentManager fragmentmanager = getFragmentManager();
	FragmentTransaction transaction = fragmentmanager.beginTransaction();
	HomeFragment homefragment = new HomeFragment();
	transaction.add(R.id.framecontainer, homefragment);
	transaction.addToBackStack(null);
	transaction.commit();
}
}
