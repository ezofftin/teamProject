<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="inc/header.jsp" %>

<!-- Carousel -->
<div id="demo" class="carousel slide" data-bs-ride="carousel">

  <!-- Indicators/dots -->
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="3"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="4"></button>
  </div>
  
  <!-- The slideshow/carousel -->
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="${ctxPath}/img/1.jpg" alt=".." class="d-block" style="width:100%">     
    </div>
    <div class="carousel-item">
      <img src="${ctxPath}/img/2.jpg" alt=".." class="d-block" style="width:100%">       
    </div>
    <div class="carousel-item">
      <img src="${ctxPath}/img/3.jpg" alt=".." class="d-block" style="width:100%">        
    </div>
    <div class="carousel-item">
      <img src="${ctxPath}/img/4.jpg" alt=".." class="d-block" style="width:100%">        
    </div>
    <div class="carousel-item">
      <img src="${ctxPath}/img/5.jpg" alt=".." class="d-block" style="width:100%">        
    </div>
    
    
  </div>
  
  <!-- Left and right controls/icons -->
  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
    <span class="carousel-control-next-icon"></span>
  </button>
</div>

<h2>Welcome To page!!</h2>

<%-- <%@ include file="inc/footer.jsp" %> --%>
<jsp:include page="inc/footer.jsp" />





