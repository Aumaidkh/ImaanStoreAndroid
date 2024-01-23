package com.imaan.common.repository

import com.imaan.common.model.OTP
import com.imaan.common.model.PhoneNumber
import com.imaan.common.wrappers.Result
import javax.inject.Inject

class OtpRepositoryImpl @Inject constructor(): IOtpRepository{
    override suspend fun requestOtpOn(phone: PhoneNumber): Result<OTP> {
        return Result.Success(data = OTP("1234"))
    }

    override suspend fun verifyOtp(
        otp: OTP,
        phone: PhoneNumber
    ): Result<IOtpRepository.VerificationStatus> {
        return Result.Success(IOtpRepository.VerificationStatus.SUCCESS)
    }
}