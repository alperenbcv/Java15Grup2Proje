package org.example.java15grup2proje.dto.request;

import org.example.java15grup2proje.entity.enums.EState;

public record ManageLeaveDto (
		String token,
		String leaveId,
		EState state,
		String rejectionReason
) {
}