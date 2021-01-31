package com.coderpwh.upload.entity;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/5/8 0008 16:02
 */
public enum  ResponseStatus {
    OK(200, "成功"),
    DATA_CREATE_ERROR(100, "新增数据失败"),
    DATA_REQUERY_ERROR(101, "查询数据失败"),
    DATA_UPDATED_ERROR(102, "更新数据失败"),
    DATA_DELETED_ERROR(103, "删除数据失败"),
    DATA_INPUT_ERROR(104, "数据未输入"),
    PARAMETER_VALIDATION(105, "参数验证失败-{0}"),
    PARAMETER_ERROR(106, "参数错误"),
    INVALID_CLIENT_ID(300, "clientID无效"),
    INVALID_USER_NAME(301, "用户名错误"),
    INVALID_PASSWORD(302, "密码错误"),
    INVALID_TOKEN(303, "access_token无效"),
    NO_AUTHORIZATION(304, "无Authorization传入"),
    FALL_BACK(501, "无法访问服务，该服务可能由于某种未知原因被关闭。请重启服务！"),
    SESSION_ERROR(900, "用户会话已过期!"),
    DATABASE_ERROR(999, "数据库异常！"),
    UNKNOW_SYSTEM_ERROR(1000, "系统未知异常"),
    TEL_FORMAT_ERROR(1001, "手机号格式错误"),
    TEL_REGISTED(1002, "手机号已注册"),
    PASSWORD_FORMAT_ERROR(1003, "密码格式为6-12位字母数字组合"),
    CAPTCHA_EXPIRED(1004, "短信验证码已过期"),
    CAPTCHA_ERROR(1005, "短信验证码错误"),
    LOGIN_FAILED(1006, "账号或密码错误"),
    TEL_NOT_REGISTED(1007, "手机号未注册"),
    OPENID_NOTNULL_ERROR(1010, "openId不能为空"),
    OPENID_NOTHAS(1011, "OpenId未注册"),
    USER_NOT_AUTHORIZE(1012, "此用戶未在京东小白授权"),
    JD_ACCESSRIGHT_FAIL(1013, "调用京东权益查询失败"),
    JD_UNBIND_FAIL(1013, "调用京东小白解绑失败"),
    JD_CREDITSCORE_NOT_MATCH(1014, "您的小白信用分不足75分，暂时不能申请订单。"),
    FILE_UPLOAD_FAILED(1008, "上传失败"),
    FILE_FETCH_FAILED(1009, "文件获取失败"),
    LOGIN_FORBIDDEN(4000, "登录失效"),
    PASSWORD_CONFIRM_ERROR(1007, "两次密码输入不一致"),
    SMS_CHANNEL_ERROR(5001, "获取短信发送渠道失败"),
    SMS_SEND_FAILED(5002, "短信发送失败"),
    SMS_SEND_FREQUENTLY(5003, "短信发送频繁，请5分钟后尝试"),
    SMS_SEND_LIMITED(5004, "短信发送次数已到上限"),
    SERVICE_CALL_OVERTIME(8000, "服务不可用或超时"),
    RISK_NOT_SUPPORT_TYPE(8001, "暂不支持这种审批类型"),
    SIGN_ADD_ORG_ACC_FAILED(6001, "添加企业签约账户失败"),
    SIGN_ADD_USER_ACC_FAILED(6002, "添加用户签约账户失败"),
    SIGN_ADD_ORG_SEAL_FAILED(6003, "添加企业签约印章失败"),
    SIGN_ADD_USER_SEAL_FAILED(6004, "添加用户签约印章失败"),
    WEI_XIN_ORDER_QUERY_FAILED(100000, "微信支付查询订单接口通讯失败"),
    WEI_XIN_ORDER_QUERY_ERROR(100001, "微信支付查询订单接口异常"),
    PRODUCT_HAS_CONFIG_ERROR(5005, "该产品存在未开始或进行中的活动配置"),
    BANNER_ADD_COUNT_ERROR(5006, "当前添加Banner的数量已达上限"),
    BANNER_DELETE_COUNT_ERROR(5007, "当前只有一个Banner不能删除"),
    TPFIELDVALUE_NAME_EXIST_ERROR(5008, "名称不能重复"),
    TPFIELDVALUE_ACCOUNTPHONE_EXIST_ERROR(5009, "账号不能重复"),
    COUPONS_NOT_EXISTS(5010, "优惠券码不存在"),
    COUPONS_OUT_DATE(5011, "不在有效期内"),
    COUPONS_UN_USABLE(5012, "优惠券不可用"),
    COUPON_NOT_EXISTS(5013, "优惠券不存在"),
    COUPONS_UPPER_LIMIT(5014, "优惠券领取已达上限"),
    TOKEN_ERROR(40001, "token失效"),
    UNKNOW_NETWORK_ERROR(7222, "网络异常，请检查网络"),
    WITHDRAWAL_APPLICATION(7223, "提现申请中，请等待处理"),
    WITHDRAWAL_ERROR(7224, "提现异常"),
    WITHDRAWAL_MONEY_ERROR(7225, "可提现金额校验异常"),
    WITHDRAWAL_NUM_ERROR(7226, "单次提现金额校验异常"),
    WITHDRAWAL_CLOCK_ERROR(7227, "余额不足"),
    ORDER_NOT_FUOND(7228, "订单不存在"),
    MERCHANTUSER_NOT_FUOND(7229, "商户不存在"),
    APPLY_WITHDRAWAL_ERROR(7230, "变动可提现额度失败"),
    STORE_NOT_FOUND(7231, "未获取到门店 信息"),
    MERCHANTUSER_TOKENDES_ERROR(7232, "商户登陆验证失败"),
    MERCHANTUSER_NOT_LOGIN(7233, "商户未登陆"),
    WITHDRAWAL_NOT_FOUND(7234, "未查询到流水记录"),
    MERCHANTQRCODE_NOT_FUOND(7235, "无效二维码"),
    MERCHANTQRCODE_EXPIRATION(7236, "二维码已失效"),
    PROFITSETTING_NOT_FUOND(7237, "未获取到提成规则"),
    SELLPRICE_ERROR(7238, "设置的销售价不合理"),
    SETTLEMENT_NOT_FUOND(7239, "未获取到结算规则"),
    MAINACCOUNT_NOT_FUOND(7240, "店铺未配置主账号"),
    ALIPAY_CREDIT_PAY_ERROR(120000, "支付宝芝麻信用支付接口异常"),
    ALIPAY_TRADE_QUERY_ERROR(130000, "支付宝订单查询接口异常"),
    JD_AGREEMENT_TRADE_ERROR(140000, "京东代扣接口异常"),
    ALIPAY_FUND_AUTH_ERROR(150000, "支付宝资金授权查询接口异常"),
    ALIPAY_FUND_AUTH_PAY_ERROR(160000, "支付宝资金授权转支付接口异常"),
    ALIPAY_TRADE_FAIL_ERROR(170000, "退款失败"),
    ALIPAY_TRADE_REFUND_ERROR(170100, "统一收单交易退款接口异常"),
    USER_INFO_ERROR(180000, "获取用户信息失败"),
    FDD_GET_PERSON_VERIFY_URL_ERROR(190000, "获取个人实名认证地址异常"),
    FDD_GET_PERSON_VERIFY_URL_FAIL_ERROR(190100, "获取个人实名认证地址失败"),
    FDD_GET_COMPANY_VERIFY_URL_ERROR(190200, "获取企业实名认证地址异常"),
    FDD_GET_COMPANY_VERIFY_URL_FAIL_ERROR(190300, "获取企业实名认证地址失败"),
    FDD_CONTRACT_TEMPLATE_UPLOAD_ERROR(190200, "合同模板上传失败"),
    FDD_APPLY_CERT_ERROR(200000, "申请证书异常"),
    FDD_APPLY_CERT_FAIL_ERROR(200100, "申请证书失败"),
    FDD_GENERATE_CONTRACT_ERROR(210000, "合同生成异常"),
    FDD_GENERATE_CONTRACT_FAIL_ERROR(210100, "合同生成失败"),
    FDD_CONTRACT_UPLOAD_FAIL_ERROR(210200, "合同上传失败"),
    FDD_SIGN_URL_FAIL_ERROR(220000, "获取手动签署URL失败"),
    FDD_REGISTER_ACCOUNT_ERROR(230000, "注册法大大账号异常"),
    FDD_SIGN_AUTOMATICALLY_FAIL_ERROR(240000, "自动签署失败"),
    FDD_SIGN_AUTOMATICALLY_ERROR(240100, "自动签署异常"),
    FDD_ARCHIVE_CONTRACT_FAIL_ERROR(250000, "合同归档失败"),
    FDD_ARCHIVE_CONTRACT_ERROR(250100, "合同归档异常"),
    WX_H5_GET_SIGN_ERROR(260000, "微信H5支付获取签名异常"),
    FDD_PERSON_APPLY_CA_ERROR(270000, "申请个人CA证书异常"),
    FDD_COMPANY_APPLY_CA_ERROR(280000, "申请企业CA证书异常"),
    FDD_CHECK_ID_CARD_ERROR(290000, "身份证验证异常"),
    WAREHOUSE_STATE_ERROR(15010, "状态流转异常,请刷新页面后重试"),
    WAREHOUSE_REFRESH(15011, "请刷新页面后重试"),
    WAREHOUSE_NO_COMMONDITY(15013, "当前订单号未查到物料信息"),
    WAREHOUSE_NO_PICK(15012, "当前订单号未查到拣货记录");

    private int code;
    private String message;

    private ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
