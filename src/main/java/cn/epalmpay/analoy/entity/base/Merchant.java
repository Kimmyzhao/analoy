package cn.epalmpay.analoy.entity.base;

import java.util.Date;

public class Merchant {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column merchant.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column merchant.agentno
     *
     * @mbggenerated
     */
    private String agentno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column merchant.agentname
     *
     * @mbggenerated
     */
    private String agentname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column merchant.createdAt
     *
     * @mbggenerated
     */
    private Date createdat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column merchant.updateAt
     *
     * @mbggenerated
     */
    private Date updateat;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column merchant.id
     *
     * @return the value of merchant.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column merchant.id
     *
     * @param id the value for merchant.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column merchant.agentno
     *
     * @return the value of merchant.agentno
     *
     * @mbggenerated
     */
    public String getAgentno() {
        return agentno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column merchant.agentno
     *
     * @param agentno the value for merchant.agentno
     *
     * @mbggenerated
     */
    public void setAgentno(String agentno) {
        this.agentno = agentno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column merchant.agentname
     *
     * @return the value of merchant.agentname
     *
     * @mbggenerated
     */
    public String getAgentname() {
        return agentname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column merchant.agentname
     *
     * @param agentname the value for merchant.agentname
     *
     * @mbggenerated
     */
    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column merchant.createdAt
     *
     * @return the value of merchant.createdAt
     *
     * @mbggenerated
     */
    public Date getCreatedat() {
        return createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column merchant.createdAt
     *
     * @param createdat the value for merchant.createdAt
     *
     * @mbggenerated
     */
    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column merchant.updateAt
     *
     * @return the value of merchant.updateAt
     *
     * @mbggenerated
     */
    public Date getUpdateat() {
        return updateat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column merchant.updateAt
     *
     * @param updateat the value for merchant.updateAt
     *
     * @mbggenerated
     */
    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }
}