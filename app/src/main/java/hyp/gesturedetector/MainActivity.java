package hyp.gesturedetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 使用GestureDetector进行手势识别
 * 手势交互过程
 * 1、触屏一刹那，出发MotionEvent
 * 2、被OnTouchListener监听，在onTouch中获得MotionEvent对象
 * 3、GestureDetector转发MotionEvent对象到OnGestureListener
 * 4、OnGestureListener获取该对象，根据该对象封装的信息作出合适的反馈
 *
 *
 *
 * MotionEvent
 * --用于封装手势、触摸笔等事迹
 * --内部封装用于记录横轴和纵轴坐标的属性
 *
 * GestureDetector
 * --识别各种手势
 * 按下、移动、抬起
 * 两个监听器 OnGestureListener处理单击OnDoubleTapListener处理双击
 *
 * OnGestureListener
 * --手势交互监听接口，提供多个抽象方法
 * --根据GestureDetector的手势识别结果调用相对应的方法
 *
 */
public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private GestureDetector mGestureDetector;
    class myGestureListener extends SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX()-e2.getX()>50){
                Toast.makeText(MainActivity.this,"从右往左滑",Toast.LENGTH_SHORT).show();
            }else if(e2.getX()-e1.getX()>50){
                Toast.makeText(MainActivity.this,"从左往右滑",Toast.LENGTH_SHORT).show();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mGestureDetector = new GestureDetector(new myGestureListener());
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            //捕获到触摸屏幕发的事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }


}
