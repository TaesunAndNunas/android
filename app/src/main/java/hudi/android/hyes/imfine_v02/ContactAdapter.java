package hudi.android.hyes.imfine_v02;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hyes on 2015. 3. 29..
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private int mOriginalHeight = 0;
    private boolean mIsViewExpanded = false;

        private List<ContactInfo> contactList;

        public ContactAdapter(List<ContactInfo> contactList) {
            this.contactList = contactList;
        }


    public void onClick(final View v) {
        if (mOriginalHeight == 0) {
            mOriginalHeight = v.getHeight();
        }
        ValueAnimator valueAnimator;
        if (v.getHeight() < (mOriginalHeight + (int) (mOriginalHeight * 1.5))) {
            valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 1.5));
        } else {
            valueAnimator = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 1.5), mOriginalHeight);
        }
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().height = value.intValue();
                v.requestLayout();
            }
        });
        valueAnimator.start();

    }




        @Override
        public int getItemCount() {
            return contactList.size();
        }

        @Override
        public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
            ContactInfo ci = contactList.get(i);
            contactViewHolder.vName.setText(ci.name);
            contactViewHolder.vSurname.setText(ci.surname);
            contactViewHolder.vEmail.setText(ci.email);


            BitmapFactory.Options options = new BitmapFactory.Options();
            options.outHeight = 100;
            options.outWidth = 100;
            Bitmap bm = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.diaper2, options);

            contactViewHolder.vTitle.setImageBitmap(bm);

                    //contactViewHolder.vTitle.setImageDrawable(ci.name + " " + ci.surname);
        }

        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_view, viewGroup, false);

            return new ContactViewHolder(itemView);
        }

        public static class ContactViewHolder extends RecyclerView.ViewHolder {

            protected TextView vName;
            protected TextView vSurname;
            protected TextView vEmail;
            protected ImageView vTitle;

//            FrameLayout frame;
//
//            public MyViewHolder(View itemView) {
//
//                super(itemView);
//                frame = (FrameLayout) itemView.findViewById(R.id.base);
//
//            }



            public ContactViewHolder(View v) {
                super(v);
                vName =  (TextView) v.findViewById(R.id.txtName);
                vSurname = (TextView)  v.findViewById(R.id.txtSurname);
                vEmail = (TextView)  v.findViewById(R.id.txtEmail);
                vTitle = (ImageView) v.findViewById(R.id.title);
            }
        }
    }

