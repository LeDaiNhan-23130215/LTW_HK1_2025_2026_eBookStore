package controllers;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

// Đường dẫn để gọi servlet này sẽ là: /read-book
@WebServlet("/read-book")
public class ReadBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Lấy thông tin từ JSP gửi lên (ID sách và loại đọc: full/thử)
        String bookId = request.getParameter("id");
        String type = request.getParameter("type"); // "full" hoặc "preview"

        // GIẢ SỬ file sách bạn lưu cứng trong thư mục server (hoặc lấy path từ MySQL)
        // Lưu ý: Đường dẫn này phải trỏ đúng nơi bạn lưu file .pdf trên ổ cứng server
        String pathToFile = "C:/ebooks/" + bookId + ".pdf";
        File fileGoc = new File(pathToFile);

        if (!fileGoc.exists()) {
            response.getWriter().write("Lỗi: Không tìm thấy file sách!");
            return;
        }

        // 2. Thiết lập phản hồi là file PDF để trình duyệt hiểu
        response.setContentType("application/pdf");
        // Dòng này giúp trình duyệt hiển thị luôn (inline) thay vì tải về
        response.setHeader("Content-Disposition", "inline; filename=sach.pdf");

        // 3. Xử lý PDFBox (Phần quan trọng nhất)
        try (PDDocument document = Loader.loadPDF(fileGoc);
             OutputStream out = response.getOutputStream()) {

            if ("preview".equals(type)) {
                // --- LOGIC CẮT FILE (ĐỌC THỬ) ---
                // Tạo file mới chứa 10 trang đầu
                try (PDDocument previewDoc = new PDDocument()) {
                    int limit = Math.min(10, document.getNumberOfPages());
                    for (int i = 0; i < limit; i++) {
                        previewDoc.importPage(document.getPage(i));
                    }
                    // Gửi file cắt gọn về trình duyệt
                    previewDoc.save(out);
                }
            } else {
                // --- LOGIC FULL (ĐỌC HẾT) ---
                // Gửi nguyên file gốc về trình duyệt
                document.save(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}