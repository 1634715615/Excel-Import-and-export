/**
 * 
 */
package com.lin.springboot.util;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * @author Administrator
 * 生产二维码图片流
 * Email：【1634715615@qq.com】
 * 创建人 ：【xufulin】
 * 创建时间 ：【2019年6月14日 上午10:16:18 】
 * 包名[pageName]:com.lin.springboot.util
 * ClassName：【Excel_Import_and_export】
 *
 */
public class QRCodeUtil {
    
	/**
	 * 
	 * @param content 要生产的内容
	 * @param response  响应对象
	 * @return
	 */
    public static BitMatrix generateQRCodeStream(String content,HttpServletResponse response,int ... widthAndHeight) {
        //给相应添加头部信息，主要告诉浏览器返回的是图片流
        response.setHeader("Cache-Control", "no-store");
        // 不设置缓存
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //设置图片的文字编码以及内边框
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //编码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //边框距
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix;
        try {
            //参数分别为：编码内容、编码类型、图片宽度、图片高度，设置参数
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,  widthAndHeight.length < 1?300:widthAndHeight[0], widthAndHeight.length < 2?300:widthAndHeight[1],hints);
        }catch(WriterException e) {
            e.printStackTrace();
            return null;
        }
        return bitMatrix;
        
    }

}