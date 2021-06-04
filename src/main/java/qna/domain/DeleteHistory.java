package qna.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import qna.domain.question.Answer;
import qna.domain.question.Question;

@Entity
@Table
public class DeleteHistory extends AbstractEntity {

	@Enumerated(EnumType.STRING)
    private ContentType contentType;

	private Long contentId;

	@ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_delete_history_to_user"))
	private User deletedBy;

    protected DeleteHistory() {}

    private DeleteHistory(ContentType answer, Long contentId, User deletedBy) {
        this.contentType = answer;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
    }

    public DeleteHistory(Question question) {
        this(ContentType.QUESTION, question.getId(), question.writer());
    }

    public DeleteHistory(Answer answer) {
        this(ContentType.ANSWER, answer.getId(), answer.writer());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) &&
                contentType == that.contentType &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contentId, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistory{" +
                "id=" + id +
                ", contentType=" + contentType +
                ", contentId=" + contentId +
                ", deletedBy=" + deletedBy.getId() +
                ", createAt=" + createdAt +
                '}';
    }
}
