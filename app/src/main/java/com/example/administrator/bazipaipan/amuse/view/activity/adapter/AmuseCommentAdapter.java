package com.example.administrator.bazipaipan.amuse.view.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.bazipaipan.R;
import com.example.administrator.bazipaipan.amuse.view.activity.model.AmuseCategory;
import com.example.administrator.bazipaipan.amuse.view.activity.model.ChatComment;

import java.util.List;

/**
 * Created by 王中阳 on 2015/12/16.
 */
public class AmuseCommentAdapter extends RecyclerView.Adapter<AmuseCommentAdapter.RecyclerHolder> {
    /**
     * 1 inflater
     * 2context
     * 3datas   get set方法
     * --
     * 4 构造器
     * 5 data的 get set方法
     */
    private LayoutInflater mInflater;
    private Context mContext;
    private List<ChatComment> mdatas;


    public AmuseCommentAdapter(Context mContext) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
    }

    public List<ChatComment> getMdatas() {
        return mdatas;
    }

    public void setMdatas(List<ChatComment> mdatas) {
        this.mdatas = mdatas;
        notifyDataSetChanged(); //观察者模式
    }

    //通过inflater引入item布局文件
    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_comment_share, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        //从数据源中抽取javabean
        ChatComment bean = mdatas.get(position);
        if (bean == null) {
            return;
        }
        if (bean.getChat_pointer() == null) {
            return;
        }
        if (bean.getChat_pointer().getMyUser_pointer() == null || bean.getChat_pointer().getAmuseDetail_pointer() == null) {
            return;
        }
        if (bean.getChat_pointer().getMyUser_pointer().getAvatar() != null) {
            bean.getChat_pointer().getMyUser_pointer().getAvatar().loadImageThumbnail(mContext, holder.user_head, 50, 50, 100);
        } else {
            holder.user_head.setImageResource(R.drawable.augur_head);
        }

        holder.user_name.setText(bean.getChat_pointer().getMyUser_pointer().getUsername());
        holder.comment_time.setText(bean.getCommentTime());
        holder.comment_content.setText(bean.getCommnetMessage());

    }

    @Override
    public int getItemCount() {
        if (mdatas != null && mdatas.size() > 0) {
            return mdatas.size();
        }
        return 0;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView user_name, comment_time, comment_content;
        ImageView user_head;

        public RecyclerHolder(View itemView) {
            super(itemView);
            user_head = (ImageView) itemView.findViewById(R.id.iv_comment_head);
            user_name = (TextView) itemView.findViewById(R.id.tv_comment_user);
            comment_time = (TextView) itemView.findViewById(R.id.tv_comment_time);
            comment_content = (TextView) itemView.findViewById(R.id.tv_comment_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mCallBack.onItemClicked(getAdapterPosition());
                }
            });
        }
    }

    public interface IClickListener {
        void onItemClicked(int position);
    }
}
