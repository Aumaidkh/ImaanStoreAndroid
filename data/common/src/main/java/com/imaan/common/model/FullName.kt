package com.imaan.common.model

data class FullName(val value: String,val error: String?){
    companion object {
        val Empty = FullName("",null)
    }
}
