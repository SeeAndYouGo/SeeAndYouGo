import styled from "@emotion/styled";
import React from "react";

const MyRadio = styled.input`
	accent-color: black;
	-ms-transform: scale(1.5) /* IE 9 */;
	-webkit-transform: scale(1.5) /* Chrome, Safari, Opera */;
	transform: scale(1.5);
`;

const ReviewMenuInfo = () => {
	return (
		<>
			<div>
				<MyRadio
					type="radio"
					name="menu"
					value="menu1"
					id="menu1"
					defaultChecked
				/>
				<label htmlFor="menu1" style={{ padding: "0px 5px" }}>
					학생식당
				</label>
				<MyRadio
					style={{ accentColor: "black" }}
					type="radio"
					name="menu"
					id="menu2"
					value="menu2"
				/>
				<label htmlFor="menu2" style={{ padding: "0px 5px" }}>
					교직원식당
				</label>
				<div
					style={{
						margin: "10px 0px",
                        padding: "15px",
                        borderRadius: "10px",
						width: "100%",
						backgroundColor: "white",
					}}
				>
					<p style={{fontSize:12, margin:"0px 0px 4px 0px", color:"#555"}}>오늘의 메뉴</p>
					<p style={{fontSize:18, margin:0}}>김치찌개 & 계란후라이</p>
					<p style={{fontSize:12, margin:0, fontWeight:400, color:"#777"}}>생선까스, 어묵채볶음, 건파래무침, 깍두기</p>
				</div>
			</div>
		</>
	);
};

export default ReviewMenuInfo;
