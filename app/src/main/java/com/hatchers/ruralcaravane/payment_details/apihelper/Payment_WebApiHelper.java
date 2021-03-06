package com.hatchers.ruralcaravane.payment_details.apihelper;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hatchers.ruralcaravane.app.MyApplication;
import com.hatchers.ruralcaravane.constants.WebServiceUrls;
import com.hatchers.ruralcaravane.payment_details.database.PaymentDetailsHelper;
import com.hatchers.ruralcaravane.payment_details.database.PaymentTable;
import com.hatchers.ruralcaravane.pref_manager.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Nikam on 20/01/2018.
 */

public class Payment_WebApiHelper {

    public static boolean uploadPaymentDataToServer(final Activity activity, final SweetAlertDialog sweetAlertDialog)
    {

       /* final PaymentTable paymentTable = PaymentDetailsHelper.getUnUploadPaymentData(activity);
        if(paymentTable==null)
        {
            sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            sweetAlertDialog.setTitleText("Successfully uploaded");
            sweetAlertDialog.setConfirmText("Ok");
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            });

            return false;
        }

        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlUploadPayment,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Payment Details added successfully")) {

                            JSONObject jsonObject = responce.getJSONObject("result");

                            paymentTable.setPayment_idValue(jsonObject.getString("id"));
                            paymentTable.setPaymentUniqueIdValue(jsonObject.getString("PAYMENT_ID"));
                            paymentTable.setAmountValue(jsonObject.getString("AMOUNT"));
                            paymentTable.setCustomerIdValue(jsonObject.getString("CUSTOMER_ID"));
                            paymentTable.setDateOfPaymentValue(jsonObject.getString("DATE_OF_PAYMENT"));
                            paymentTable.setKitchenIdValue(jsonObject.getString("KITCHEN_ID"));
                            paymentTable.setDateOfPaymentValue(jsonObject.getString("DATE_OF_PAYMENT"));
                            paymentTable.setPaymentTypeValue(jsonObject.getString("PAYMENT_TYPE"));
                            paymentTable.setReceiptNoValue(jsonObject.getString("RECEIPT_NO"));
                            paymentTable.setUploadDateValue(jsonObject.getString("UploadDate"));


                            if (PaymentDetailsHelper.updatePaymentDetailsData(activity, paymentTable)) {

                                Toast.makeText(activity,"Payment Data Updated Successfully..",Toast.LENGTH_SHORT).show();
                                uploadPaymentDataToServer(activity,sweetAlertDialog);
                            } else {

                                Toast.makeText(activity,"Payment Data Updation Failed ",Toast.LENGTH_SHORT).show();
                                sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                sweetAlertDialog.setTitleText("Update failed");
                                sweetAlertDialog.setConfirmText("Ok");
                                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                    }
                                });

                            }


                        }
                        else
                        {
                            Toast.makeText(activity,"Response Failed ",Toast.LENGTH_SHORT).show();
                            sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            sweetAlertDialog.setTitleText("Response failed");
                            sweetAlertDialog.setConfirmText("Ok");
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            });

                        }

                    }
                    else
                    {
                        Toast.makeText(activity,"Response Failed ",Toast.LENGTH_SHORT).show();
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText("Response failed");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });

                    }
                } catch (JSONException e)
                {
                    Toast.makeText(activity,"JSON Error ",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("JSON Error");
                    sweetAlertDialog.setConfirmText("Ok");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });

                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(activity,"Timeout Error ",Toast.LENGTH_SHORT).show();
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Check internet connection");
                    sweetAlertDialog.setConfirmText("Ok");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });

                }
                else if (error instanceof ServerError)
                {
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Server Error");
                    sweetAlertDialog.setConfirmText("Ok");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });

                }
                else if (error instanceof NetworkError)
                {
                    Toast.makeText(activity,"Network Error",Toast.LENGTH_SHORT).show();
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Network Error");
                    sweetAlertDialog.setConfirmText("Ok");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });

                }
                else if (error instanceof ParseError)
                {
                    Toast.makeText(activity,"Parse Error",Toast.LENGTH_SHORT).show();
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Parse Error");
                    sweetAlertDialog.setConfirmText("Ok");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });

                }
                else
                {
                    Toast.makeText(activity,"Unknown Error",Toast.LENGTH_SHORT).show();
                    sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Unkonwn error");
                    sweetAlertDialog.setConfirmText("Ok");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    });

                }

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                *//*format=json&mobile=9975294782&password=user@123&
                CUSTOMER_ID=2321&PAYMENT_ID=23214&AMOUNT=234124&
                DATE_OF_PAYMENT=a&KITCHEN_ID=asadx&
                PAYMENT_TYPE=asxasxas&RECEIPT_NO=asxasx*//*
                params.put("format","json");
                params.put("CUSTOMER_ID",paymentTable.getCustomerIdValue());
                params.put("PAYMENT_ID",paymentTable.getPaymentUniqueIdValue());
                params.put("AMOUNT",paymentTable.getTotalPaidValue());
                params.put("DATE_OF_PAYMENT",paymentTable.getDateOfPaymentValue());
                params.put("KITCHEN_ID",paymentTable.getKitchenIdValue());
                params.put("PAYMENT_TYPE",paymentTable.getPaymentTypeValue());
                params.put("RECEIPT_NO",paymentTable.getReceiptNoValue());
                params.put("mobile",new PrefManager(activity).getMobile());
                params.put("password",new PrefManager(activity).getPassword());


                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);
       */ return true;
    }
}
