import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
/*
 * pdf 转 图片
 */
public class Icepdf {
    public static void pdf2Pic(String pdfPath, String path, String url) throws MalformedURLException {
        Document document = new Document();
        if(StringUtils.isNotBlank(pdfPath)){
            URL pdfUrl = new URL(url);
            document.setUrl(pdfUrl);

        }else{
            document.setFile(pdfPath);
        }

        float scale = 2.5f;//缩放比例
        float rotation = 0f;//旋转角度

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                String imgName = i + ".jpg";
                System.out.println(imgName);
                File file = new File(path + imgName);
                ImageIO.write(rendImage, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }
    public static void main(String[] args) throws MalformedURLException {
        String filePath = "D:\\workspace\\testpdftoimage\\22D8867F4A04928CACC065125F0BBC2A_20180929160331.pdf";
        String url ="http://172.0.16.102:8080/WLZFZP/22D8867F4A04928CACC065125F0BBC2A_20180929160331.pdf";
        pdf2Pic(filePath, "D:\\",url);
    }
}
