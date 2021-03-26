package com.maple.baiduocr.entity;

public class TokenEntity {

    /**
     * refresh_token : 25.d1eab890722bd97d3f8d77d74a5c60c7.315360000.1932023427.282335-23590854
     * expires_in : 2592000
     * session_key : 9mzdA8yrjZp/qZq3+LMA8pXxZYAF2mHww9ylwNPyVs/z46nOqigc6AdNMt779Lx3uAdAgBvYaFr5B/H2+3Ilr3GCRmuYpQ==
     * access_token : 24.8ff0e04afa5ce36e5ef4081a6d3b9849.2592000.1619255427.282335-23590854
     * scope : public vis-ocr_ocr brain_ocr_scope brain_ocr_general brain_ocr_general_basic vis-ocr_business_license brain_ocr_webimage brain_all_scope brain_ocr_idcard brain_ocr_driving_license brain_ocr_vehicle_license vis-ocr_plate_number brain_solution brain_ocr_plate_number brain_ocr_accurate brain_ocr_accurate_basic brain_ocr_receipt brain_ocr_business_license brain_solution_iocr brain_qrcode brain_ocr_handwriting brain_ocr_passport brain_ocr_vat_invoice brain_numbers brain_ocr_business_card brain_ocr_train_ticket brain_ocr_taxi_receipt vis-ocr_household_register vis-ocr_vis-classify_birth_certificate vis-ocr_台湾通行证 vis-ocr_港澳通行证 vis-ocr_机动车购车发票识别 vis-ocr_机动车检验合格证识别 vis-ocr_车辆vin码识别 vis-ocr_定额发票识别 vis-ocr_保单识别 vis-ocr_机打发票识别 vis-ocr_行程单识别 brain_ocr_vin brain_ocr_quota_invoice brain_ocr_birth_certificate brain_ocr_household_register brain_ocr_HK_Macau_pass brain_ocr_taiwan_pass brain_ocr_vehicle_invoice brain_ocr_vehicle_certificate brain_ocr_air_ticket brain_ocr_invoice brain_ocr_insurance_doc brain_formula brain_ocr_facade brain_ocr_meter brain_doc_analysis brain_ocr_webimage_loc wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test权限 vis-classify_flower lpq_开放 cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base smartapp_mapp_dev_manage iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify smartapp_opensource_openapi smartapp_opensource_recapi fake_face_detect_开放Scope vis-ocr_虚拟人物助理 idl-video_虚拟人物助理 smartapp_component smartapp_search_plugin avatar_video_test
     * session_secret : b8322b7c6f8820090087211619348bf8
     */

    private String refresh_token;
    private int expires_in;
    private String session_key;
    private String access_token;
    private String scope;
    private String session_secret;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSession_secret() {
        return session_secret;
    }

    public void setSession_secret(String session_secret) {
        this.session_secret = session_secret;
    }
}
