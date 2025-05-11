/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.NhaCungCapDAO;
import java.util.List;
import models.NhaCungCapDTO;

/**
 *
 * @author vinhp
 */
public class NhaCungCap {
    private NhaCungCapDAO nccDAO;

    public NhaCungCap() throws Exception {
        this.nccDAO = new NhaCungCapDAO();
    }

    public boolean themNhaCungCap(NhaCungCapDTO ncc) {
        try {
            return nccDAO.themNhaCungCap(ncc);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaNhaCungCap(int maNCC) {
        try {
            return nccDAO.xoaNhaCungCap(maNCC);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaNhaCungCap(NhaCungCapDTO ncc) {
        try {
            return nccDAO.suaNhaCungCap(ncc);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public NhaCungCapDTO timNhaCungCap(int maNCC) {
        try {
            return nccDAO.timNhaCungCap(maNCC);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhaCungCapDTO> dsNhaCungCap() {
        try {
            return nccDAO.dsNhaCungCap();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
