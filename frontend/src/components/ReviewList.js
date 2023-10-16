import React, { useState, useEffect } from "react";
import styled from "@emotion/styled";
import ReviewItem from "./ReviewItem";

const CheckBoxInput = styled.input`
	display: none;

	&:checked + label {
		background: #111;
	}
	&:checked + label::after {
		color: white;
	}
`;
const CheckBoxLabel = styled.label`
	cursor: pointer;
	display: inline-block;
	width: 15px;
	height: 15px;
	background-color: #d9d9d9;
	position: relative;
	top: 1.5px;
	border-radius: 3px;
	&::after {
		content: "✔";
		color: #fff;
		font-size: 10px;
		width: 15px;
		height: 15px;
		text-align: center;
		position: absolute;
		left: 0;
		top: 0;
	}
`;

const SortingSelect = styled.select`
	width: 105px;
	height: 20px;
	font-size: 12px;
	float: right;
	padding: 0px 0.5em;
	border: 0px;
	border-radius: 5px;
	font-family: inherit;
	background: url("/assets/images/arrow.png") no-repeat 95% 50%;
	background-size: contain;
	background-color: white;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	::-ms-expand {
		display: none;
	}
`;

const ReviewList = () => {
	const [review, setReview] = useState([]);
	useEffect(() => {
		const fetchData = async () => {
			// "http:localhost:8080/"
			const res = await fetch(`/assets/json/totalReview.json`, {
				headers: {
					"Content-Type": "application/json",
				},
				method: "GET",
			});
			const result = await res.json();
			return result;
		};
		fetchData().then((data) => {
			setReview(data);
		});
	}, []);

	return (
		<>
			<div>
				<CheckBoxInput type="checkbox" id="check" />
				<CheckBoxLabel htmlFor="check" />
				<label htmlFor="check" style={{ marginLeft: 5, cursor:"pointer" }}>
					사진 리뷰만 보기
				</label>
				<SortingSelect>
					<option value="latest">최근 등록순</option>
					<option value="ealiest">오래된순</option>
					<option value="like">좋아요 많은순</option>
					<option value="lowRate">별점 낮은순</option>
					<option value="highRate">별점 높은순</option>
				</SortingSelect>
			</div>
			<div style={{marginBottom:30}}>
				{review.map((nowReview, idx) => (
					<ReviewItem
						user={nowReview.writer}
						time={nowReview.madeTime}
						rate={nowReview.rate}
						content={nowReview.comment}
						img={nowReview.image}
						key={idx}
					/>
				))}
			</div>
			<div className="blankSpace">
				&nbsp;
			</div>
		</>
	);
};

export default ReviewList;
