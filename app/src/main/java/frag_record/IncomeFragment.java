package frag_record;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.dailycost.R;

import java.util.List;

import db.DBManager;
import db.TypeBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends BaseRecordFragment {

    @Override
    public void loadDataToGv() {
        super.loadDataToGv();

//        获取数据库当中的数据源
        List<TypeBean> inlist = DBManager.getTypeList(1);
        typeList.addAll(inlist);
        adapter.notifyDataSetChanged();
        typeTv.setText("其他");
        typeIv.setImageResource(R.mipmap.in_qt_fs);
    }

    @Override
    public void saveAccountToDB() {
        accountBean.setKind(1);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
