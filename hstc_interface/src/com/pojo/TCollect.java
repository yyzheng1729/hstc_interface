package com.pojo;

public class TCollect {
    private String collectId;

    private String openid;

    private String id;
    
    private TRecruitment tRecruitement;
    
    

    public TRecruitment gettRecruitement() {
		return tRecruitement;
	}

	public void settRecruitement(TRecruitment tRecruitement) {
		this.tRecruitement = tRecruitement;
	}

	public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId == null ? null : collectId.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
}