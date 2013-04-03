/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.manager;

import java.sql.Connection;
import java.util.List;
import tur.bean.User;
import tur.dao.DAOUser;
import tur.datasource.BDConnecion;

/**
 *
 * @author ruben
 */
public class ManagerUser {

    DAOUser dAOUser;
    User user;
    Connection cn = null;

    public ManagerUser(BDConnecion connecion) {

        this.cn = connecion.getConnection();
    }

    public List listarUser() {
        dAOUser = new DAOUser(cn);
        return dAOUser.listarUser();
    }

    public List list_User_by_Edicion(int id) {
        dAOUser = new DAOUser(cn);
        return dAOUser.list_User_by_Edicion(id);
    }

    public List list_idUsers() {
        dAOUser = new DAOUser(cn);
        return dAOUser.list_idUsers();
    }
}
