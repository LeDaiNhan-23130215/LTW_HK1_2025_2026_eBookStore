// --- PHẦN 1: CẤU HÌNH PDF.JS & BIẾN TOÀN CỤC ---

// ĐƯỜNG DẪN FILE PDF (Bạn hãy thay file của bạn vào đây)
const url = "../bookfile/atomic-habits-thay-doi-ti-hon-hieu-qua-bat-ngo-james-clear-ly-ngoc-anh-dich-6467.pdf";

let pdfDoc = null,
    pageNum = 1,
    pageRendering = false,
    pageNumPending = null,
    scale = 1.5,
    canvas = document.getElementById('the-canvas'),
    ctx = canvas.getContext('2d');

// Trạng thái chế độ thêm ghi chú
let isNoteMode = false; 

// Cấu hình Worker cho PDF.js
pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.16.105/pdf.worker.min.js';

// --- PHẦN 2: CÁC HÀM XỬ LÝ PDF (RENDER, ZOOM, PAGE) ---

// Tải tài liệu
pdfjsLib.getDocument(url).promise.then(function(pdfDoc_) {
    pdfDoc = pdfDoc_;
    document.getElementById('page_count').textContent = pdfDoc.numPages;
    renderPage(pageNum);
});

function renderPage(num) {
    pageRendering = true;
    pdfDoc.getPage(num).then(function(page) {
        var viewport = page.getViewport({scale: scale});
        canvas.height = viewport.height;
        canvas.width = viewport.width;

        // Render PDF lên Canvas
        var renderContext = {
            canvasContext: ctx,
            viewport: viewport
        };
        var renderTask = page.render(renderContext);

        renderTask.promise.then(function() {
            pageRendering = false;
            if (pageNumPending !== null) {
                renderPage(pageNumPending);
                pageNumPending = null;
            }
        });
    });
    document.getElementById('page_num').textContent = num;
}

function queueRenderPage(num) {
    if (pageRendering) {
        pageNumPending = num;
    } else {
        renderPage(num);
    }
}

// Các nút điều khiển PDF
document.getElementById('prev').addEventListener('click', () => {
    if (pageNum <= 1) return;
    pageNum--;
    queueRenderPage(pageNum);
});

document.getElementById('next').addEventListener('click', () => {
    if (pageNum >= pdfDoc.numPages) return;
    pageNum++;
    queueRenderPage(pageNum);
});

document.getElementById('zoomIn').addEventListener('click', () => { scale += 0.2; renderPage(pageNum); });
document.getElementById('zoomOut').addEventListener('click', () => { if(scale > 0.5) scale -= 0.2; renderPage(pageNum); });
document.getElementById('zoomReset').addEventListener('click', () => { scale = 1.5; renderPage(pageNum); });


// --- PHẦN 3: CHỨC NĂNG GHI CHÚ (NOTE FUNCTION) ---

const btnAddNote = document.getElementById('btn-add-note');
const pdfWrapper = document.getElementById('pdf-wrapper');
const annotationLayer = document.getElementById('annotation-layer');

// 1. Bật/Tắt chế độ thêm ghi chú
btnAddNote.addEventListener('click', () => {
    isNoteMode = !isNoteMode;
    if (isNoteMode) {
        btnAddNote.style.backgroundColor = '#ffcc00'; // Đổi màu nút để báo hiệu đang active
        pdfWrapper.style.cursor = 'crosshair'; // Đổi con trỏ chuột
    } else {
        btnAddNote.style.backgroundColor = '#fffa90';
        pdfWrapper.style.cursor = 'default';
    }
});

// 2. Sự kiện Click vào trang PDF để tạo ghi chú
annotationLayer.addEventListener('click', (e) => {
    if (!isNoteMode) return;

    // Lấy toạ độ click chuột so với khung PDF
    const rect = pdfWrapper.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;

    createNote(x, y);

    // Sau khi tạo xong, tắt chế độ thêm ghi chú
    isNoteMode = false;
    btnAddNote.style.backgroundColor = '#fffa90';
    pdfWrapper.style.cursor = 'default';
});

// 3. Hàm hiển thị Note lên màn hình
function createNote(x, y) {
    const note = document.createElement('div');
    note.className = 'note';
    note.style.left = x + 'px';
    note.style.top = y + 'px';

    // Nội dung HTML của tờ giấy nhớ
    note.innerHTML = `
        <div class="note-header">
            <div class="note-tools">
                <button onclick="formatText('bold')"><b>B</b></button>
                <button onclick="formatText('italic')"><i>I</i></button>
                <button onclick="formatText('underline')"><u>U</u></button>
            </div>
            <button class="btn-close" onclick="deleteNote(this)">X</button>
        </div>
        <div class="note-content" contenteditable="true">
            Ghi chú mới...
        </div>
    `;

    annotationLayer.appendChild(note);
}

// 4. Hàm xoá ghi chú
function deleteNote(btn) {
    // Tìm thẻ cha lớn nhất là .note và xoá nó
    const note = btn.closest('.note');
    if(note) note.remove();
}


// --- PHẦN 4: CHỨC NĂNG KIỂU CHỮ (TEXT STYLING FUNCTION) ---

// Hàm thực thi lệnh định dạng văn bản (B, I, U)
// Hàm này tách biệt hoàn toàn với logic tạo note
window.formatText = function(command) {
    // document.execCommand là lệnh chuẩn để format text trong vùng contenteditable
    document.execCommand(command, false, null);
    
    // Giữ focus lại vào vùng soạn thảo sau khi bấm nút
    const selection = window.getSelection();
    if (selection.rangeCount > 0) {
        const range = selection.getRangeAt(0);
        // Kiểm tra xem người dùng có đang trỏ chuột vào trong note không
        if (range.commonAncestorContainer.parentElement.closest('.note-content')) {
           // Đã áp dụng format
        }
    }
};