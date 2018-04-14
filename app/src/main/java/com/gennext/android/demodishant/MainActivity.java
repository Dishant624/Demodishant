package com.gennext.android.demodishant;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.vlk.multimager.activities.GalleryActivity;
import com.vlk.multimager.utils.Constants;
import com.vlk.multimager.utils.Image;
import com.vlk.multimager.utils.Params;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private GridLayout lnrImages;
    private CardView btnAddPhots, addinamge;
    private Button btnSaveImages;
    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;
    private Bitmap resized;
    Bitmap bitmap;
    private final int PICK_IMAGE_MULTIPLE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lnrImages = (GridLayout) findViewById(R.id.lnrImages);
        //btnAddPhots = (Button)findViewById(R.id.btnAddPhots);
        addinamge = findViewById(R.id.addimage);




        addinamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                Params params = new Params();
                params.setCaptureLimit(1);
                params.setPickerLimit(1);
                params.setToolbarColor(getResources().getColor(R.color.colorPrimary));
                params.setActionButtonColor(getResources().getColor(R.color.actionbarvtn));
                params.setButtonTextColor(getResources().getColor(R.color.textcolor));
                intent.putExtra(Constants.KEY_PARAMS, params);
                startActivityForResult(intent, Constants.TYPE_MULTI_PICKER);
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Choose application"), 100);
            }
        });
        //btnSaveImages = (Button)findViewById(R.id.btnSaveImages);
//        btnAddPhots.setOnClickListener(this);
//        btnSaveImages.setOnClickListener(this);
    }

    //  @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btnAddPhots:
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Choose application"), 100);
//
//
////                Intent intent = new Intent(MainActivity.this,CustomPhotoGalleryActivity.class);
////                startActivityForResult(intent,PICK_IMAGE_MULTIPLE);
//                break;
//            case R.id.btnSaveImages:
//                if(imagesPathList !=null){
//                    if(imagesPathList.size()>1) {
//                        Toast.makeText(MainActivity.this, imagesPathList.size() + " no of images are selected", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(MainActivity.this, imagesPathList.size() + " no of image are selected", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(MainActivity.this," no images are selected", Toast.LENGTH_SHORT).show();
//                }
//                break;
//        }
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Constants.TYPE_MULTI_CAPTURE:
                ArrayList<Image> imagesList2 = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);

                break;
            case Constants.TYPE_MULTI_PICKER:
                ArrayList<Image> imagesList = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);
                Log.d("imagesize", "onActivityResult: " + imagesList.size());
//            int i=lnrImages.getChildCount();
//            for(int v=i;v>0;v++){
//
//                lnrImages.removeViewAt();
//
//            }

//            for(int i=0;i<imagesList.size();i++){
//                Log.d("imagesize", "onActivityResult: "+imagesList.get(i).imagePath);

                bitmap = decodeFile(imagesList.get(0).imagePath);
                //bitmap = BitmapFactory.decodeFile(imagesList.get(i).imagePath);
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
//                Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

                FrameLayout frameLayout=new FrameLayout(this);

                ImageView imageView = new ImageView(this);
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                int dimensionInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
                param.height = dimensionInDp;
                param.width = dimensionInDp;
                param.rightMargin = 2;
                param.topMargin = 2;
                param.setGravity(Gravity.CENTER);
                imageView.setBackground(getResources().getDrawable(R.drawable.doshline));
                //GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(200, 200);
                //layoutParams.setMargins(20, 20, 20, 20);
                imageView.setLayoutParams(param);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageBitmap(bitmap);
                lnrImages.addView(imageView);
                int childCount = lnrImages.getChildCount();

                for (int i= 1; i < childCount; i++){
                    final int p=i;
                    ImageView container = (ImageView) lnrImages.getChildAt(i);
                    container.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View view){
                            // your click code here


                            Dialog  dialog = new Dialog(MainActivity.this,android.R.style.Theme_Light);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.getWindow().getAttributes().windowAnimations = R.style.Widget_AppCompat_ListPopupWindow;
                            //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            //dialog.getWindow().setBackgroundDrawableResource(R.color.dialogtransprant);


//                            dialog.setContentView(R.layout.imagepager_layout);
//
//
//                            ViewPager viewPager=dialog.findViewById(R.id.viewpager3);
//
//
//                            FullimageAdapter imagepagerAdapter3=new FullimageAdapter(mContext,integers);
//                            viewPager.setAdapter(imagepagerAdapter3);
//                            viewPager.setCurrentItem(position);
//
//                            dialog.show();
                        }
                    });
                }
//            }
                break;
        }
    }

    private Bitmap decodeFile(String imgPath) {
        Bitmap b = null;
        int max_size = 1000;
        File f = new File(imgPath);
        try {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream fis = new FileInputStream(f);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();
            int scale = 1;
            if (o.outHeight > max_size || o.outWidth > max_size) {
                scale = (int) Math.pow(2, (int) Math.ceil(Math.log(max_size / (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
            }
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();
        } catch (Exception e) {
        }
        return b;
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if(requestCode == PICK_IMAGE_MULTIPLE){
//                imagesPathList = new ArrayList<String>();
//                String[] imagesPath = data.getStringExtra("data").split("\\|");
//                try{
//
//                    lnrImages.removeAllViews();
//                }catch (Throwable e){
//                    e.printStackTrace();
//                }
//                for (int i=0;i<imagesPath.length;i++){
//                    imagesPathList.add(imagesPath[i]);
//                    yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);
//                    ImageView imageView = new ImageView(this);
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(300,300));
//                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                    imageView.setImageBitmap(yourbitmap);
//                    imageView.setAdjustViewBounds(true);
//                    lnrImages.addView(imageView);
//                }
//            }
//        }
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            // When an Image is picked
//            if (requestCode == 100 && resultCode == RESULT_OK
//                    && null != data) {
//                // Get the Image from data
//
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//                // imagesUriList = new ArrayList<Uri>();
//                //encodedImageList.clear();
//                if(data.getData()!=null){
//
//                    Uri mImageUri=data.getData();
//
//                    // Get the cursor
//                    Cursor cursor = getContentResolver().query(mImageUri,
//                            filePathColumn, null, null, null);
//                    // Move to first row
//                    cursor.moveToFirst();
//
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    //imageURI  = cursor.getString(columnIndex);
//                    cursor.close();
//
//                }else {
//                    if (data.getClipData() != null) {
//                        ClipData mClipData = data.getClipData();
//                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
//                        Log.d("count", "onActivityResult: "+lnrImages.getChildCount());
//                        lnrImages.removeAllViews();
//
//                        for (int i = 0; i < mClipData.getItemCount(); i++) {
//                            ClipData.Item item = mClipData.getItemAt(i);
//                            Uri uri = item.getUri();
//                            mArrayUri.add(uri);
//                            // Get the cursor
//                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
//                            // Move to first row
//                            cursor.moveToFirst();
//
//                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                            //imageURI  = cursor.getString(columnIndex);
//                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                            ImageView imageView = new ImageView(this);
//                            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(200, 200);
//                            layoutParams.setMargins(20,20,20,20);
//                            imageView.setLayoutParams(layoutParams);
//
//                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                            imageView.setImageBitmap(bitmap);
//                            imageView.setAdjustViewBounds(true);
//                            lnrImages.addView(imageView);
//
////                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
////                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
////                            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
////                            //encodedImageList.add(encodedImage);
//                            cursor.close();
//
//                        }
//                        // noImage.setText("Selected Images: " + mArrayUri.size());
//                    }
//                }
//            } else {
//                Toast.makeText(this, "You haven't picked Image",
//                        Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Log.d("checkerror", "onActivityResult: "+e.toString());
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
//        }
//    }

}