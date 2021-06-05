package com.apcachef.networking;

import com.apcachef.model.ForgetPasswordResponse;
import com.apcachef.model.ForgetRequest;
import com.apcachef.model.RenewPayments.RenewPaymentRequest;
import com.apcachef.model.RenewPayments.RenewPaymentStatus;
import com.apcachef.model.RenewStatus;
import com.apcachef.model.RenewStatusRequest;
import com.apcachef.model.aboutUs.AboutUsResponse;
import com.apcachef.model.account.ChangePasswordRequest;
import com.apcachef.model.account.ChangePasswordResponse;
import com.apcachef.model.clogin.GCLoginResponse;
import com.apcachef.model.contact.ContactResponse;
import com.apcachef.model.contactUs.PostQueryRequest;
import com.apcachef.model.contactUs.PostQueryResponse;
import com.apcachef.model.detail.CourseDetailRequest;
import com.apcachef.model.detail.CourseDetailResponse;
import com.apcachef.model.editProfile.UpdateProfileRequest;
import com.apcachef.model.editProfile.UpdateProfileResponse;
import com.apcachef.model.egift.EGiftResponse;
import com.apcachef.model.egift.SendEGiftRequest;
import com.apcachef.model.egift.SendEGiftResponse;
import com.apcachef.model.faq.FAQResponse;
import com.apcachef.model.goodCustomer.GoodCustomerRequest;
import com.apcachef.model.goodCustomer.GoodCustomerResponse;
import com.apcachef.model.home.HomePageResponse;
import com.apcachef.model.login.LoginRequest;
import com.apcachef.model.login.LoginResponse;
import com.apcachef.model.myCourses.MyCoursesResponse;
import com.apcachef.model.payment.PaymentRequest;
import com.apcachef.model.payment.PaymentResponse;
import com.apcachef.model.register.RegisterRequest;
import com.apcachef.model.register.RegisterResponse;
import com.apcachef.model.shortLesson.ShortLessonResponse;
import com.apcachef.model.subscribed.SubscribeRequest;
import com.apcachef.model.subscribed.SubscribedResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("Login/UserLogin")
    Call<LoginResponse> postLoginRequest(@Body LoginRequest loginRequest);

    @POST("Good_customer/UserLogin")
    Call<GCLoginResponse> postLoginRequestGoodCustomer(@Body LoginRequest loginRequest);

    @POST("Login/registration")
    Call<RegisterResponse> postRegisterRequest(@Body RegisterRequest registerRequest);

    @POST("Pages/send_enquiry")
    Call<PostQueryResponse> postQueryRequest(@Body PostQueryRequest postQueryRequest);

    @POST("Login/forgot_password")
    Call<ForgetPasswordResponse> postForgetRequest(@Body ForgetRequest forgetRequest);

    @POST("Good_customer/forgot_password")
    Call<ForgetPasswordResponse> postForgetRequestGoodCustomer(@Body ForgetRequest forgetRequest);

    @GET("Pages/get_contact_us")
    Call<ContactResponse> getContactDetails();

    @GET("Pages/about_us")
    Call<AboutUsResponse> getAboutUs();

    @GET("Pages/privacy")
    Call<AboutUsResponse> getPrivacyPolicy();

    @GET("Pages/terms_and_condition")
    Call<AboutUsResponse> getTermsCondition();

    @POST("Pages/home_page")
    Call<HomePageResponse> getHomePageData(@Body GoodCustomerRequest subscribeRequest);

    @POST("Courses/detail")
    Call<CourseDetailResponse> getDetailData(@Body CourseDetailRequest courseDetailRequest);

    @GET("Pages/get_faq")
    Call<FAQResponse> getFAQData();

    @POST("Courses/do_payment")
    Call<PaymentResponse> getPaymentData(@Body PaymentRequest paymentRequest);

    @POST("Courses/renew_now")
    Call<PaymentResponse> getPaymentDataRenew(@Body PaymentRequest paymentRequest);

    @POST("Courses/update_profile")
    Call<UpdateProfileResponse> updateProfile(@Body UpdateProfileRequest updateProfileRequest);

    @POST("Courses/update_password")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @POST("Courses/check_is_subscribe")
    Call<SubscribedResponse> isSubscribed(@Body SubscribeRequest subscribeRequest);

    @POST("Good_customer/so_good_customer")
    Call<GoodCustomerResponse> getGoodCustomerData(@Body GoodCustomerRequest courseDetailRequest);

    @POST("Pages/my_courses")
    Call<MyCoursesResponse> getMyCourses(@Body GoodCustomerRequest subscribeRequest);

    @GET("Sendegift/send_egift")
    Call<EGiftResponse> getEGift();

    @POST("Sendegift/reg_egift")
    Call<SendEGiftResponse> sendGift(@Body SendEGiftRequest sendEGiftRequest);

    @POST("ShortVideo/short_lessons")
    Call<ShortLessonResponse> getShortLesson(@Body CourseDetailRequest sendEGiftRequest);

    @POST("ShortVideo/video_det")
    Call<CourseDetailResponse> getVideoDetail(@Body CourseDetailRequest sendEGiftRequest);

    @POST("Pages/home_page")
    Call<RenewStatus> getRenewStatus( @Body RenewStatusRequest params);

  @POST("Courses/renew_now")
    Call<RenewPaymentStatus> getRenewPaymentStatus(@Body RenewPaymentRequest params);


}
