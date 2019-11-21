package awsm.awsmizng.u.languageappgeneralbody;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppCreditsFragment extends Fragment {
    public AppCreditsFragment() {
        // Required empty public constructor
    }
    
    View hideView, showView;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_credits, container, false);
        hideView = view.findViewById(R.id.vImage);
        showView = view.findViewById(R.id.vBack);
        hideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animHideCircular(hideView);
                animShowCircular(showView);
            }
        });
        view.findViewById(R.id.btWhatsapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendWhatsappMessage();
            }
        });
        view.findViewById(R.id.btFacebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFacebookProfile();
            }
        });
        view.findViewById(R.id.btProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendWhatsappMessage();
            }
        });
        return view;
    }
    
    private void animHideCircular(final View myView) {
        // get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;
        // get the initial radius for the clipping circle
        float initialRadius = (float) Math.hypot(cx, cy);
        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0f);
        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });
        // start the animation
        anim.start();
    }
    
    private void animShowCircular(final View myView) {
        // get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;
        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);
        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0f, finalRadius);
        // make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }
    
    private void sendWhatsappMessage() {
        String text = "Hi, this is Aayush Jain from " + getString(R.string.app_name) + ". I would love to mention that you have done an incredible job with the app.";
        String toNumber = "919810115481"; //attach 91 for indian number
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
        startActivity(intent);
    }
    
    private void openFacebookProfile() {
        String url = "https://www.facebook.com/AWSMnesss";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
