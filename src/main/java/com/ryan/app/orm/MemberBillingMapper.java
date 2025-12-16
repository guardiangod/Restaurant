package com.ryan.app.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ryan.app.model.Member;

public class MemberBillingMapper extends MemberMapper {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = super.mapRow(rs, rowNum);
		member.setStreetAddress(rs.getString("street_address"));
		member.setCity(rs.getString("city"));
		member.setState(rs.getString("state"));
		member.setPostal(rs.getString("postal"));
		member.setPhone(rs.getString("phone_number"));
		member.setEmail(rs.getString("email"));
		member.setCcNumber(rs.getString("cc_number"));
		member.setCardType(rs.getString("card_type"));
		member.setCcExpiryYear(rs.getInt("cc_expiry_yy"));
		member.setCcExpiryMonth(rs.getInt("cc_expiry_mm"));
		member.setCcHolderName(rs.getString("cc_holder_name"));
		
		return member;
	}
}
