
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestPdfToImage {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestPdfToImage.class);
    private static final String PAGE_INDEX_SEPARATOR = ",";


    public void pdfToImage(String srcFile, String destPath
            , String format, Float dpi, Boolean merge) throws IOException {
        File file = new File(srcFile);
        //默认输出路径为源文件所在文件夹
        if(destPath == null || destPath.isEmpty()){
            destPath = file.getParent();
        }

        List<BufferedImage> images = pdfToImage(file, dpi == null ? 96f : dpi);
        if(images == null || images.isEmpty()){
            return ;
        }
        //默认为jpg
        format = format == null || format.isEmpty() ? "jpg" : format;
        String pdfFileName = file.getName();
        pdfFileName = pdfFileName.substring(0, pdfFileName.indexOf('.'));
        StringBuilder sb = new StringBuilder();
        //合并多张图片为一张
        merge = merge == null ? false : merge;
        if(merge) {
            BufferedImage image = mergeImages(images);
            images.clear();
            images.add(image);
        }
        //保存到本地
        for (int i = 0, len = images.size(); i < len; i++) {
            //输出格式: [文件夹路径]/[pdf文件名]_0001.jpg
            ImageIO.write(images.get(i), format, new File(
                    sb.append(destPath).append(File.separator)
                            .append(pdfFileName).append("_").append(String.format("%04d", i + 1))
                            .append(".").append(format).toString()));
            sb.setLength(0);
        }
    }

    private BufferedImage mergeImages(List<BufferedImage> images) {
        int width = 0, height = 0;
        for(BufferedImage image : images){
            width = image.getWidth() > width ? image.getWidth() : width;
            height += image.getHeight();
        }
        BufferedImage pdfImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = pdfImage.createGraphics();
        height = 0;
        for(BufferedImage image :images){
            g2d.drawImage(image, (width - image.getWidth()) / 2, height, image.getWidth(), image.getHeight(), null);
            height += image.getHeight();
        }
        g2d.dispose();
        return pdfImage;
    }

    private List<BufferedImage> pdfToImage(File file, float dpi)  throws IOException {
        List<BufferedImage> imgList = null;
        PDDocument pdDocument = null;
        BufferedImage image;
        try {
            pdDocument = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            int numPages = pdDocument.getNumberOfPages();
            imgList = new ArrayList<BufferedImage>();
            for (int i = 0; i < numPages; i++) {
                image = renderer.renderImageWithDPI(i, dpi);
                if (null != image) {
                    imgList.add(image);
                }
            }
        } catch (IOException e) {
            LOGGER.error("convert pdf pages to images failed.", e);
            throw e;
        } finally {
            try {
                if (null != pdDocument) {
                    pdDocument.close();
                }
            } catch (IOException e) {
                LOGGER.error("close IO failed when convert pdf pages to images.", e);
                throw e;
            }
        }
        return imgList;

    }

    public static void main(String[] args) throws IOException {
        String srcFile = "D:\\workspace\\testpdftoimage\\52BAF82B191491D62981429BAC2CA4B5_20180929151159.pdf";
        new TestPdfToImage().pdfToImage(srcFile, null, null, 96f, true);
    }


}
