package com.galaxy.hotelpro.Model;

public class PosUserModel {

    private int _userid;
    private String _short;
    private String _name;
    private String _password;
    private boolean _use_tabletuser;
    private String _is_authorize;

    public PosUserModel() {
    }

    public PosUserModel(int _userid, String _short, String _name, String _password, boolean _use_tabletuser, String _is_authorize) {
        this._userid = _userid;
        this._short = _short;
        this._name = _name;
        this._password = _password;
        this._use_tabletuser = _use_tabletuser;
        this._is_authorize = _is_authorize;
    }


    public int get_userid() {
        return _userid;
    }

    public void set_userid(int _userid) {
        this._userid = _userid;
    }

    public String get_short() {
        return _short;
    }

    public void set_short(String _short) {
        this._short = _short;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public boolean get_use_tabletuser() {
        return _use_tabletuser;
    }

    public void set_use_tabletuser(boolean _use_tabletuser) {
        this._use_tabletuser = _use_tabletuser;
    }

    public String get_is_authorize() {
        return _is_authorize;
    }

    public void set_is_authorize(String _is_authorize) {
        this._is_authorize = _is_authorize;
    }
}
