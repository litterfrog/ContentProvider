package com.fxp.activities;

import java.util.Arrays;

import com.fxp.contentprovider.DBHelper;
import com.fxp.contentprovider.ProviderConstant;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends Activity {
	private LinearLayout accountForm;
	private LinearLayout userInfoForm;
	private LinearLayout foodForm;
	private EditText et_email;
	private EditText et_password;
	private EditText et_username;
	private EditText et_userphone;
	private EditText et_useraddress;
	private EditText et_useraccid;
	private EditText et_foodname;
	private EditText et_foodsummary;
	private EditText et_foodphone;
	private EditText et_foodaddress;
	private EditText et_foodlabel;
	private RadioButton rd_male;
//	private RadioButton rd_female;
	private ListView lv_chihuo;
	private int kind=0;
	private DBHelper dbHelper=new DBHelper(this);
//    private ContentResolver contentResolver;
    private SimpleCursorAdapter adapter = null;
    private int[] items=new int[]{
			R.id.tv_item_1,
			R.id.tv_item_2,
			R.id.tv_item_3,
			R.id.tv_item_4,
			R.id.tv_item_5,
			R.id.tv_item_6,
			R.id.tv_item_7,
			R.id.tv_item_8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }
    private void findViews() {
    	accountForm=(LinearLayout) findViewById(R.id.account_form);
    	userInfoForm=(LinearLayout) findViewById(R.id.userinfo_form);
    	foodForm=(LinearLayout) findViewById(R.id.food_form);
    	et_email=(EditText) findViewById(R.id.et_email);
    	et_password=(EditText) findViewById(R.id.et_password);
    	et_username=(EditText) findViewById(R.id.et_username);
    	et_userphone=(EditText) findViewById(R.id.et_userphone);
    	et_useraddress=(EditText) findViewById(R.id.et_useraddress);
    	et_useraccid=(EditText)findViewById(R.id.et_useraccid);
    	et_foodname=(EditText) findViewById(R.id.et_foodname);
    	et_foodsummary=(EditText) findViewById(R.id.et_foodsummary);
    	et_foodphone=(EditText) findViewById(R.id.et_foodphone);
    	et_foodaddress=(EditText) findViewById(R.id.et_foodaddress);
    	et_foodlabel=(EditText) findViewById(R.id.et_foodlabel);
    	rd_male=(RadioButton) findViewById(R.id.rd_male);
//    	rd_female=(RadioButton) findViewById(R.id.rd_female);
    	lv_chihuo=(ListView) findViewById(R.id.lv_chihuo);
	}
    private void clearEditText() {
    	et_email.setText("");
    	et_password.setText("");
    	et_username.setText("");
    	et_userphone.setText("");
    	et_useraddress.setText("");
    	et_useraccid.setText("");
    	et_foodname.setText("");
    	et_foodsummary.setText("");
    	et_foodphone.setText("");
    	et_foodaddress.setText("");
    	et_foodlabel.setText("");
	}
    public void onClick(View v){

    	switch (v.getId()) {
		case R.id.btn_add_account:
			kind=0;
			accountForm.setVisibility(View.VISIBLE);
			userInfoForm.setVisibility(View.GONE);
			foodForm.setVisibility(View.GONE);
			break;
		case R.id.btn_add_food:
			kind=1;
			accountForm.setVisibility(View.GONE);
			userInfoForm.setVisibility(View.GONE);
			foodForm.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_add_userinfo:
			kind=2;
			accountForm.setVisibility(View.GONE);
			userInfoForm.setVisibility(View.VISIBLE);
			foodForm.setVisibility(View.GONE);
			break;
		case R.id.btn_submit:
			doSubmit();			
			break;
		case R.id.btn_select:
			doSelect();
			break;
		default:
			break;
    	}
/*    	//通过contentResolver进行查找
        contentResolver = MainActivity.this.getContentResolver();
        Cursor cursor = contentResolver.query(
          ProviderConstant.ACCOUNT_URI, new String[] {
        		  
        		  ProviderConstant.TACCOUNT_ID,ProviderConstant.TACCOUNT_EMAIL, ProviderConstant.TACCOUNT_PASSWORD}, null, null, null);
        while (cursor.moveToNext()) {
             Toast.makeText(
            		 MainActivity.this,
            cursor.getString(cursor.getColumnIndex(ProviderConstant.TACCOUNT_ID))
                    + " "
                    + cursor.getString(cursor.getColumnIndex(ProviderConstant.TACCOUNT_EMAIL))
                    + " "
                    + cursor.getString(cursor.getColumnIndex(ProviderConstant.TACCOUNT_PASSWORD))
                    ,
                   Toast.LENGTH_SHORT).show();
             }
           cursor.close();  //查找后关闭游标
*/    }

	@SuppressWarnings("deprecation")
	private void doSelect() {
		Cursor query=null;
		String []columns=null;
		switch (kind) {
		case 0:
			columns= new String[]{
					ProviderConstant.TACCOUNT_ID +" as _id",
					ProviderConstant.TACCOUNT_EMAIL,
					ProviderConstant.TACCOUNT_PASSWORD
			};
			query = dbHelper.query(ProviderConstant.TACCOUNT,columns, null, null, null);
			columns[0]="_id";
			break;
		case 1:
			columns= new String[]{
					ProviderConstant.TFOOD_ID +" as _id",
					ProviderConstant.TFOOD_NAME,
					ProviderConstant.TFOOD_PHONE,
					ProviderConstant.TFOOD_PICTRUE_PATH,
					ProviderConstant.TFOOD_SUMMARY,
					ProviderConstant.TFOOD_ADDRESS,
					ProviderConstant.TFOOD_LABEL
			};
			query = dbHelper.query(ProviderConstant.TFOOD,columns, null, null, null);
			columns[0]="_id";
			break;
		case 2:
			columns= new String[]{
					ProviderConstant.TUSERINFO_ID +" as _id",
					ProviderConstant.TUSERINFO_NAME,
					ProviderConstant.TUSERINFO_PHONE,
					ProviderConstant.TUSERINFO_SEX,
					ProviderConstant.TUSERINFO_ADDRESS,
					ProviderConstant.TUSERINFO_ACCID
			};
			query = dbHelper.query(ProviderConstant.TUSERINFO,columns, null, null, null);
			columns[0]="_id";
			break;
		default:
			break;
		}
		if(null==query||null==columns){
			return;
		}
		
		adapter=new SimpleCursorAdapter(this,R.layout.item_main, query, columns,Arrays.copyOfRange(items, 0, columns.length));
		lv_chihuo.setAdapter(adapter);
		
		
	}
	private void doSubmit() {
    	ContentValues values=new ContentValues();
    	long insertRows=0;
		switch (kind) {
		case 0:
			Editable mEmail=et_email.getText();
			Editable mPassword = et_password.getText();
	        if(TextUtils.isEmpty(mEmail)||TextUtils.isEmpty(mPassword)){
	        	return;
	        }
		    //先对数据库进行添加数据		        
	        values.put(ProviderConstant.TACCOUNT_EMAIL,mEmail.toString());
	        values.put(ProviderConstant.TACCOUNT_PASSWORD,mPassword.toString());
	        insertRows = dbHelper.add(ProviderConstant.TACCOUNT, values);
	        if(0!=insertRows){
	        	Toast.makeText(getApplicationContext(), "提交成功",Toast.LENGTH_SHORT).show();
	        }
			break;
		case 1:
			Editable mFoodname=et_foodname.getText();
			Editable mFoodsummary = et_foodsummary.getText();
			Editable mFoodphone = et_foodphone.getText();
			Editable mFoodaddress = et_foodaddress.getText();
			Editable mFoodlabel = et_foodlabel.getText();
			
			if(TextUtils.isEmpty(mFoodname)||TextUtils.isEmpty(mFoodsummary)||TextUtils.isEmpty(mFoodphone)){
				return;
			}
			values.put(ProviderConstant.TFOOD_NAME,mFoodname.toString());
			values.put(ProviderConstant.TFOOD_SUMMARY,mFoodsummary.toString());
			values.put(ProviderConstant.TFOOD_PHONE,mFoodphone.toString());
			values.put(ProviderConstant.TFOOD_PICTRUE_PATH,"chihuo/"+mFoodname.toString());
			values.put(ProviderConstant.TFOOD_ADDRESS,mFoodaddress.toString());
			values.put(ProviderConstant.TFOOD_LABEL,mFoodlabel.toString());
			insertRows = dbHelper.add(ProviderConstant.TFOOD, values);
			if(0!=insertRows){
				Toast.makeText(getApplicationContext(), "提交成功",Toast.LENGTH_SHORT).show();
			}
			break;
		case 2:
			Editable mUsername=et_username.getText();
			Editable mUserphone = et_userphone.getText();
			Editable mUseraccid = et_useraccid.getText();
			Editable mUseraddress = et_useraddress.getText();
			if(TextUtils.isEmpty(mUsername)||TextUtils.isEmpty(mUserphone)||TextUtils.isEmpty(mUseraccid)){
				return;
			}
			int mSex=rd_male.isChecked()?ProviderConstant.ISMALE:ProviderConstant.ISFEMALE;
			values.put(ProviderConstant.TUSERINFO_NAME,mUsername.toString());
			values.put(ProviderConstant.TUSERINFO_PHONE,mUserphone.toString());
			values.put(ProviderConstant.TUSERINFO_ACCID,Integer.valueOf(mUseraccid.toString()));
			values.put(ProviderConstant.TUSERINFO_SEX,mSex);
			values.put(ProviderConstant.TUSERINFO_ADDRESS,mUseraddress.toString());
			insertRows = dbHelper.add(ProviderConstant.TUSERINFO, values);
			if(0!=insertRows){
				Toast.makeText(getApplicationContext(), "提交成功",Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}

		clearEditText();
		
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
