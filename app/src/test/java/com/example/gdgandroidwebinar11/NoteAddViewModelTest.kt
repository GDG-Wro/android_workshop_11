package com.example.gdgandroidwebinar11

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gdgandroidwebinar11.data.INotesService
import com.example.gdgandroidwebinar11.models.Note
import com.example.gdgandroidwebinar11.note.NoteAddViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import java.util.*

class NoteAddViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun viewModel_addNote_success() = runBlocking {
        val testText = "test"
        val testDate = Date(2020 - 1900, 9, 15)
        val mockedService: INotesService = mock(INotesService::class.java)
        var callArgument: Note? = null

        whenever(mockedService.addNote(any())).then {
            callArgument = it.getArgument<Note>(0)
            Unit
        }

        val viewModel = NoteAddViewModel(mockedService)
        viewModel.noteText.value = testText
        viewModel.noteDate.value = testDate

        viewModel.addNote { }

        verify(mockedService).addNote(any())
        assert(callArgument?.text == testText)
        assert(callArgument?.date?.equals(testDate) ?: false)
    }

    @Test
    fun viewModel_addNote_empty_title() = runBlocking {
        val testDate = Date(2020 - 1900, 9, 15)
        val mockedService: INotesService = mock(INotesService::class.java)

        val viewModel = NoteAddViewModel(mockedService)
        viewModel.noteDate.value = testDate

        viewModel.addNote { }

        verify(mockedService, never()).addNote(any())
    }
}