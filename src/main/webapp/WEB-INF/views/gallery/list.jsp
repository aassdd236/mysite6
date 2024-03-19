<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css"
	rel="stylesheet" type="text/css">

<style>
.modal {
	width: 100%; /* 가로전체 */
	height: 100%; /* 세로전체 */
	display: none; /* 시작할때 숨김처리 */
	position: fixed; /* 화면에 고정 */
	left: 0; /* 왼쪽에서 0에서 시작 */
	top: 0; /* 위쪽에서 0에서 시작 */
	z-index: 999; /* 제일위에 */
	overflow: auto; /* 내용이 많으면 스크롤 생김 */
	background-color: rgba(0, 0, 0, 0.4); /* 배경이 검정색에 반투명 */
}
/* 모달창 내용 흰색부분 */
.modal .modal-content {
	width: 800px;
	margin: 100px auto; /* 상하 100px, 좌우 가운데 */
	padding: 0px 20px 20px 20px; /* 안쪽여백 */
	background-color: #ffffff; /* 배경색 흰색 */
	border: 1px solid #888888; /* 테두리 모양 색 */
}

/* 닫기버튼 */
.modal .modal-content .closeBtn {
	text-align: right;
	color: #aaaaaa;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}
</style>
</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>갤러리</h2>
				<ul>
					<li><a href="">일반갤러리</a></li>
					<li><a href="">파일첨부연습</a></li>
				</ul>
			</div>
			<!-- //aside -->
			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->


				<div id="gallery">
					<div id="list">

						<c:if test="${ !(empty sessionScope.authUser) }">
							<button id="btnImgUpload" type="button">이미지올리기</button>
						</c:if>
						<div class="clear"></div>

						<!-- 이미지반복영역 -->

						<c:forEach items="${galleryList}" var="galleryVo">
							<ul id="viewArea" id="t-" +${galleryVo.no}>
								<li>
									<div class="view">
										<img class="imgItem" data-no="galleryVo.no"
											data-saveName="${galleryVo.saveName}"
											data-userno="${galleryVo.userNo}"
											data-content="${galleryVo.content}"
											src="${pageContext.request.contextPath}/upload/${galleryVo.saveName}">
										<div class="imgWriter">
											작성자: <strong>${galleryVo.name}</strong>
										</div>
									</div>
								</li>
							</ul>
						</c:forEach>
						<!-- 이미지반복영역 -->
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

	</div>
	<!-- //wrap -->

	<!-- 이미지등록 팝업(모달)창 -->
	<div id="addModal" class="modal">
		<div class="modal-content">
			<form action="${pageContext.request.contextPath}/gallery/upload"
				method="post" enctype="multipart/form-data">
				<div class="closeBtn">×</div>
				<div class="m-header">간단한 타이틀</div>
				<div class="m-body">
					<input type="hidden" name="userNo"
						value="${sessionScope.authUser.no}">
					<div>
						<label class="form-text">글작성</label> <input id="addModalContent"
							type="text" name="content" value="">
					</div>
					<div class="form-group">
						<label class="form-text">이미지선택</label> <input id="file"
							type="file" name="file" value="">
					</div>
				</div>

				<div class="m-footer">
					<button type="submit">저장</button>
				</div>
			</form>
		</div>
	</div>

	<!-- 이미지보기 팝업(모달)창 -->
	<div id="viewModal" class="modal">
		<div class="modal-content">
			<div class="closeBtn">×</div>
			<div class="m-header">간단한 타이틀</div>
			<div class="m-body">
				<div>
					<img id="viewModelImg" src=""> <input type="hidden" id="no"
						name="no" value="">
					<!-- ajax로 처리 : 이미지출력 위치-->
				</div>
				<div>
					<p id="viewModelContent"></p>
				</div>
			</div>
			<div class="m-footer">
				<c:choose>
					<c:when
						test="${not empty authUser and authUser.no == gallery.userNo}">
						<button id="deleteBtn">삭제</button>
					</c:when>
				</c:choose>

			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	<!-- //footer -->
</body>

<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function() {
		// 모달창 호출 버튼을 클릭했을 때
		let addModalBtn = document.querySelector("#btnImgUpload");
		addModalBtn.addEventListener("click", callModal);

		// 모달창 닫기 버튼을 클릭했을 때
		let closeBtn = document.querySelector("#addModal .closeBtn");
		closeBtn.addEventListener("click", closeModal);

		// 사진 모달창 호출 버튼을 클릭했을 때
		let viewAreaBtn = document.querySelectorAll("#viewArea");
		viewAreaBtn.addEventListener("click", viewModal);

		// 사진 모달창 닫기 버튼을 클릭했을 때
		let closeBtn02 = document.querySelector("#viewModal .closeBtn");
		closeBtn02.addEventListener("click", closeModal02);

		// 모달창에 삭제버튼을 클릭했을 때
		let deleteBtn = document.querySelector('#deleteBtn');
		deleteBtn.addEventListener("click", deleteBtn);

	});

	function callModal(event) {
		if (event.target.tagName == "BUTTON") {
			console.log("모달창 보이기");
			let addModal = document.querySelector("#addModal");
			addModal.style.display = "block";
		}
	}

	function closeModal(event) {
		let addModal = document.querySelector("#addModal");
		addModal.style.display = "none";
	}

	function viewModal(event) {
			console.log("모달창 보이기");
			let viewModal = document.querySelector("#viewModal");
			viewModal.style.display = "block";

			let noTag = document.querySelector('#no');
			noTag.value = event.target.dataset.no;

			let saveTag = document.querySelector('#viewModelImg');
			saveTag.src = "${pageContext.request.contextPath}/upload/"+ event.target.dataset.saveName;

			let contentTag = document.querySelector('#viewModelContent');
			contentTag.textContent = event.target.dataset.content;
	}

	function closeModal02(event) {
		let viewModal = document.querySelector("#viewModal");
		viewModal.style.display = "none";
	}

	function deleteBtn(event) {
		console.log("삭제 버튼 클릭");
	}
</script>

</html>

