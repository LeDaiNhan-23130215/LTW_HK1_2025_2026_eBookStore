<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vn">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Feedback</title>
    <link rel="stylesheet" href="assets/css/base.css" />
    <link rel="stylesheet" href="assets/css/components.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
    />
    <link rel="icon" type="image/png" href="assets/img/ebook-logo2.png" />
    <link rel="stylesheet" href="assets/css/home.css" />
    <link rel="stylesheet" href="assets/css/feedback.css" />
  </head>
  <body>
  <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

    <!-- Phần thẻ của feedback  -->
    <main class="feedback-container">
      <h2>Biểu mẫu phản hồi</h2>
      <p>
        Chúng tôi rất mong nhận được suy nghĩ, đề xuất, mối quan tâm hoặc vấn đề
        của bạn về bất kỳ điều gì để chúng tôi có thể cải thiện!
      </p>

      <form action="#" method="post">
        <div class="form-group">
          <label for="feedback_description" class="group-title">
            Mô tả phản hồi của bạn: <span class="required-star">*</span>
          </label>
          <textarea
            id="feedback_description"
            name="feedback_description"
            class="textarea-field"
            required
          ></textarea>
        </div>

        <button type="submit" class="submit-button">Submit</button>
      </form>
    </main>

  <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

    <script src="assets/js/component.js"></script>
  </body>
</html>
