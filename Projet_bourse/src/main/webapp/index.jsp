<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP PROJECT</title>
  <link rel="stylesheet" href="static/index.scss"/>
</head>
<body>
<div class="login">
  <div class="login-header">
    <div class="login-header-nav">
      <span class="login-link"> Madagascar ScholarShip Payment</span>
    </div>
  </div>
  <div class="login-body">
    <div class="login-body-form">
      <div class="login-form">
        <div class="login-body-title">
          <span class="welcoming">Welcome Again</span>
          <span class="connecting">Please, log in first</span>
        </div>
        <div class="login-inputs">
          <div class="simpleInput">
            <label class="label-input">email</label>
            <input class="input-simple" type="email" placeholder="your email"/>
          </div>
          <div class="simpleInput">
            <label class="label-input">password</label>
            <input class="input-simple" type="password" placeholder="your password"/>
          </div>
        </div>
        <div class="login-button">
          <div class="buttonNoIcon">
            <span><a href="/Projet_bourse_war_exploded/students">LOG IN</a></span>
          </div>
        </div>
      </div>
    </div>
    <div class="login-body-image">
      <div class="login-image"></div>
    </div>
  </div>
</div>
</body>
</html>