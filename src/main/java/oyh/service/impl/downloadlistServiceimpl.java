package oyh.service.impl;


import oyh.dao.downloadlistdao;
import oyh.dao.impl.downloadlistdaoimpl;
import oyh.pojo.downloadlist;
import oyh.service.downloadlistServicce;

import java.util.List;

public class downloadlistServiceimpl implements downloadlistServicce {
    private downloadlistdao dao=new downloadlistdaoimpl();
    @Override

    public List<downloadlist> querylist() {
        return dao.query();
    }

    @Override
    public downloadlist querforone( int id) {
        return  dao.queryone(id);
    }
}
