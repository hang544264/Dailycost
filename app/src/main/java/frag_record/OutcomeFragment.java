package frag_record;

import androidx.fragment.app.Fragment;

import com.example.dailycost.R;

import java.util.List;

import db.DBManager;
import db.TypeBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutcomeFragment extends BaseRecordFragment  {

    @Override
    public void loadDataToGv() {
        super.loadDataToGv();
//        获取数据库当中的数据源
        List<TypeBean> outlist = DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();
        typeTv.setText("其他");
        typeIv.setImageResource(R.mipmap.ic_qita_fs);
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(0);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
