import styled from "@emotion/styled";
import React, { useState, useEffect } from "react";
import StarsRating from "react-star-rate";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar as solidStar } from "@fortawesome/free-solid-svg-icons";
import { faCamera } from "@fortawesome/free-solid-svg-icons";
import { faCircleUser } from "@fortawesome/free-solid-svg-icons";

const ReviewWriteContainer = styled.form`
	width: 100%;
	background: #fff;
	padding: 15px;
	border-radius: 20px;
	margin: 10px 0 20px 0;
	float: left;
`;
const ReviewStarRating = styled.span`
	float: left;
	color: #d9d9d9;
	margin-top: -5px;
	margin-left: 10px;
	> ul {
		font-size: 20px;
	}
	> ul > li {
		margin-right: 3px;
	}
`;
const ReviewWriteInput = styled.input`
	color: #999;
	border: 1px solid #d9d9d9;
	border-radius: 10px;
	padding: 0 10px;
	height: 35px;
	outline: none;
	width: 100%;

	&::placeholder {
		font-weight: 400;
		font-size: 12px;
	}
`;
const ReviewWriteCamera = styled.label`
	color: #d9d9d9;
	font-size: 22px;
	position: absolute;
	right: 10px;
	line-height: 35px;
	cursor: pointer;
`;
const ReviewWriteButton = styled.button`
	width: 45px;
	font-size: 12px;
	background: #d9d9d9;
	color: "#777";
	border-radius: 10px;
	border: none;
	height: 35px;
	float: right;
	font-weight: 400;
	cursor: pointer;
`;

// 별점 0점인 경우 리뷰 작성 불가능하게 하기
const ReviewWrite = () => {
	const [starVal, setStarVal] = useState(0);
	return (
		<ReviewWriteContainer>
			<div style={{ width: "100%", float: "left" }}>
				<p style={{ margin: "0", float: "left", fontSize: 15 }}>별점</p>
				<ReviewStarRating>
					<StarsRating
						value={starVal}
						onChange={(value) => {
							setStarVal(value);
						}}
					/>
				</ReviewStarRating>
			</div>
			<div style={{ width: "100%" }}>
				<div
					style={{
						position: "relative",
						width: "calc(100% - 55px)",
						float: "left",
					}}
				>
					<input type="file" id="Review-file-input" hidden></input>
					<ReviewWriteInput placeholder="리뷰를 남겨주세요 :)" />
					<ReviewWriteCamera htmlFor="Review-file-input">
						<FontAwesomeIcon icon={faCamera} />
					</ReviewWriteCamera>
				</div>
				<ReviewWriteButton>작성</ReviewWriteButton>
			</div>
		</ReviewWriteContainer>
	);
};

const ReviewItemContainer = styled.div`
	width: 100%;
	background: #fff;
	padding: 7px 15px;
	border-radius: 20px;
	margin-top: 10px;
	float: left;
`;
const ReviewItemIcon = styled.p`
	float: left;
	font-size: 35px;
	color: #555;
	margin: 0 10px 0 0;
`;
const ReviewItemProfile = styled.div`
	float: left;
	margin-top: 5px;

	> p {
		margin: 0;
		font-size: 16px;
	}
	> p:last-child {
		color: #777;
		font-weight: 400;
		font-size: 12px;
		margin-top: -2px;
	}
`;
const ReviewItemStar = styled.span`
	font-size: 12px;
	margin-left: 5px;
	> svg {
		color: #ffc107;
		font-size: 15px;
		margin-right: 2px;
	}
`;
const ReviewItemContent = styled.p`
	width: 100%;
	font-size: 14px;
	font-weight: 400;
	margin: 5px 0 0 0;
`;

// backend에서 받아온 데이터를 이용하여 리뷰 아이템 생성
// 시간은 어떻게 받아올 것인지 고민해보기
// 수정, 삭제는 어떻게 백엔드와 연동할 것인지 고민해보기
const ReviewItem = ({ user, time, content, img, rate }) => {
	return (
		<ReviewItemContainer>
			<div className="Row1" style={{ width: "100%", float: "left" }}>
				<ReviewItemIcon>
					<FontAwesomeIcon icon={faCircleUser} />
				</ReviewItemIcon>
				<ReviewItemProfile>
					<p>
						{user}
						<ReviewItemStar>
							<FontAwesomeIcon icon={solidStar} />
							{rate}
						</ReviewItemStar>
					</p>
					<p>{time} 작성</p>
				</ReviewItemProfile>
			</div>
			<div className="Row2" style={{ float: "left", width: "100%" }}>
				<ReviewItemContent>{content}</ReviewItemContent>
			</div>
			<div className="Row3" style={{ width: "100%", float: "left" }}>
				<img
					src={img}
					alt="Loading.."
					style={{ maxHeight: 120, float: "left", marginTop: 5 }}
				></img>
			</div>
		</ReviewItemContainer>
	);
};

const Review = ({ idx }) => {
	const [reviewArr, setReviewArr] = useState([]);

	useEffect(() => {
		const fetchData = async () => {
			const nowUrl = `/api/topReview/restaurant${idx}`;
			// const nowUrl = "/assets/json/restaurant1Review.json";
			const res = await fetch(nowUrl, {
				headers: {
					"Content-Type": "application/json",
				},
				method: "GET",
			});
			const result = await res.json();
			return result;
		};
		fetchData().then((data) => {
			setReviewArr(data);
		});
	}, []);

	return (
		<div style={{ float: "left", marginTop: 20 }}>
			<p style={{ fontSize: 18, margin: 0 }}>오늘 메뉴의 리뷰</p>
			<ReviewWrite />

			<p style={{ fontSize: 18, margin: 0 }}>오늘의 리뷰 미리보기</p>
			{reviewArr.map((el, index) => (
				<ReviewItem
					key={index}
					user={el.writer}
					time={el.madeTime}
					rate={el.rate}
					content={el.comment}
					img={el.image}
				/>
			))}
		</div>
	);
};

export default Review;
