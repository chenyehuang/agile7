package cn.edu.jnu.agile7.ui.bill;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cn.edu.jnu.agile7.ui.dashboard.Bill;

/**
 * @author Administrator
 */
public class DataServer {
    public void Save(Context context, ArrayList<Bill> data)//context：上下文，哪个页面调用了
    {
        try {
            //字节流啥都可以
            FileOutputStream dataStream=context.openFileOutput("mydata.dat",Context.MODE_PRIVATE);//创建一个(文件)输出流对象
            ObjectOutputStream out = new ObjectOutputStream(dataStream); //对象写入流对象
            out.writeObject(data);
            out.close();
            dataStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull//这样就表示不会返回一个NULL了，至少是一个空数组(那边还可以继续使用)
    public ArrayList<Bill> Load(Context context)
    {
        ArrayList<Bill> data=new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput("mydata.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = (ArrayList<Bill>) in.readObject();//读会对象流的序列化信息，并根据对象的序列化信息建议一个对象
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

