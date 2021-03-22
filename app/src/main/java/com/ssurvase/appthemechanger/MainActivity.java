package com.ssurvase.appthemechanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.payu.base.models.ErrorResponse;
import com.payu.base.models.PayUPaymentParams;
import com.payu.checkoutpro.PayUCheckoutPro;
import com.payu.ui.model.listeners.PayUCheckoutProListener;
import com.payu.ui.model.listeners.PayUHashGenerationListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    TextView payText;
    PostViewModel postViewModel = new PostViewModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());
        Button razorpaypay = findViewById(R.id.razorpay);
        Button payu = findViewById(R.id.pay_u);
        payText = findViewById(R.id.pay_text);
        razorpaypay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        postViewModel = new PostViewModel();
        payu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPaymentPayU();
            }
        });

        postViewModel.getPostData().observe(this, new Observer<List<PojoModel>>() {
            @Override
            public void onChanged(List<PojoModel> pojoModels) {
                for (int i = 0; i<pojoModels.size(); i++){
                    Log.d("TAG", "pojoModels = "+ pojoModels.get(i).getTitle());
                    Log.d("TAG", "pojoModels = "+ pojoModels.get(i).getBody());
                }
            }
        });

    }

    public void startPaymentPayU(){
        PayUPaymentParams.Builder builder = new PayUPaymentParams.Builder();
        builder.setAmount("100")
        .setIsProduction(true)
        .setProductInfo("Production Info")
        .setKey("key")
        .setPhone("9987401222")
        .setTransactionId("121212")
        .setFirstName("Demo Name")
        .setEmail("demo@gmail.com")
        .setSurl("http://techathalon.com/")
        .setFurl("http://techathalon.com/")
        .setUserCredential("")
        .setAdditionalParams(new HashMap<String,Object>());
        //Optional, can contain any additional PG params
        PayUPaymentParams payUPaymentParams = builder.build();

        PayUCheckoutPro.open(this, payUPaymentParams, new PayUCheckoutProListener() {
            @Override
            public void onPaymentSuccess(Object o) {
                Log.d("TAG", "onPaymentSuccess = "+o);
            }

            @Override
            public void onPaymentFailure(Object o) {
                Log.d("TAG", "onPaymentFailure = "+o);
            }

            @Override
            public void onPaymentCancel(boolean b) {
                Log.d("TAG", "onPaymentCancel = "+b);
            }

            @Override
            public void onError(ErrorResponse errorResponse) {
                Log.d("TAG", "ErrorResponse = "+errorResponse.getErrorMessage());
            }

            @Override
            public void generateHash(HashMap<String, String> hashMap, PayUHashGenerationListener payUHashGenerationListener) {
                Log.d("TAG", "generateHash = "+hashMap);
            }

            @Override
            public void setWebViewProperties(WebView webView, Object o) {
                Log.d("TAG", "setWebViewProperties = "+o);
            }
        });
    }


    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            options.put("send_sms_hash",true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

           JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9******2");
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        payText.setText("Success = "+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        payText.setText("Failure = "+s);
    }
}