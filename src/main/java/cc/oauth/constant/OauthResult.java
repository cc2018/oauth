package cc.oauth.constant;


import cc.oauth.base.BaseResult;

public class OauthResult extends BaseResult {

    public OauthResult(OauthResultConstant oauthResultConstant, Object data) {
        super(oauthResultConstant.getCode(), oauthResultConstant.getMessage(), data);
    }

}
