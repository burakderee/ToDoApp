// features/task/SwipeActionCallback.kt

package com.example.todoapp.ui.task

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class SwipeActionCallback(
    private val context: Context,
    private val onSwipeLeft: (RecyclerView.ViewHolder) -> Unit,
    private val onSwipeRight: (RecyclerView.ViewHolder) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    // İkonlar ve arka plan renkleri
    private val deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete)
    private val deleteBackground = ColorDrawable(Color.parseColor("#FF0000")) // Kırmızı arka plan
    private val editIcon = ContextCompat.getDrawable(context, R.drawable.ic_edit)
    private val editBackground = ColorDrawable(Color.parseColor("#007BFF")) // Mavi arka plan

    private val deleteIconWidth = deleteIcon?.intrinsicWidth ?: 0
    private val deleteIconHeight = deleteIcon?.intrinsicHeight ?: 0
    private val editIconWidth = editIcon?.intrinsicWidth ?: 0
    private val editIconHeight = editIcon?.intrinsicHeight ?: 0


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top

        if (dX < 0) { // Sola kaydırma (silme)
            deleteBackground.setBounds(
                itemView.right + dX.toInt(),
                itemView.top,
                itemView.right,
                itemView.bottom
            )
            deleteBackground.draw(c)

            val deleteIconMargin = (itemHeight - deleteIconHeight) / 2
            val deleteIconTop = itemView.top + deleteIconMargin
            val deleteIconBottom = deleteIconTop + deleteIconHeight
            val deleteIconLeft = itemView.right - deleteIconMargin - deleteIconWidth
            val deleteIconRight = itemView.right - deleteIconMargin

            deleteIcon?.setBounds(
                deleteIconLeft,
                deleteIconTop,
                deleteIconRight,
                deleteIconBottom
            )
            deleteIcon?.draw(c)

        } else if (dX > 0) { // Sağa kaydırma (düzenleme)
            editBackground.setBounds(
                itemView.left,
                itemView.top,
                itemView.left + dX.toInt(),
                itemView.bottom
            )
            editBackground.draw(c)

            val editIconMargin = (itemHeight - editIconHeight) / 2
            val editIconTop = itemView.top + editIconMargin
            val editIconBottom = editIconTop + editIconHeight
            val editIconLeft = itemView.left + editIconMargin
            val editIconRight = editIconLeft + editIconWidth

            editIcon?.setBounds(
                editIconLeft,
                editIconTop,
                editIconRight,
                editIconBottom
            )
            editIcon?.draw(c)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        when (direction) {
            ItemTouchHelper.LEFT -> onSwipeLeft(viewHolder)
            ItemTouchHelper.RIGHT -> onSwipeRight(viewHolder)
        }
    }
}