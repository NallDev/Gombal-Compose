package com.nalldev.gombal.utils

import com.nalldev.gombal.data.local.room.model.JobBookmarkedEntity
import com.nalldev.gombal.data.network.model.DataItem
import com.nalldev.gombal.domain.model.JobModel

object JobMapper {
    fun toDomain(data: DataItem): JobModel {
        return JobModel(
            id = data.slug,
            companyName = data.companyName ?: "Unknown",
            title = data.title,
            description = data.description ?: "-",
            remote = data.remote,
            tags = data.tags,
            location = data.location ?: "-",
            date = CommonHelper.convertTimestampToDate(data.createdAt),
            url = data.url
        )
    }

    fun localToDomain(entity: JobBookmarkedEntity) = JobModel(
        id = entity.id,
        companyName = entity.companyName,
        title = entity.title,
        url = entity.url,
        description = entity.description,
        tags = entity.tags,
        remote = entity.remote,
        location = entity.location,
        date = entity.date,
        isBookmarked = true
    )

    fun toJobBookmarkedData(job: JobModel): JobBookmarkedEntity {
        return JobBookmarkedEntity(
            id = job.id,
            companyName = job.companyName,
            title = job.title,
            description = job.description,
            remote = job.remote,
            tags = job.tags,
            location = job.location,
            date = job.date,
            url = job.url
        )
    }
}