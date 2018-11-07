package com.jueggs.andutils.permission

sealed class PermissionStatus

object Granted : PermissionStatus()
object Denied : PermissionStatus()
object Blocked : PermissionStatus()