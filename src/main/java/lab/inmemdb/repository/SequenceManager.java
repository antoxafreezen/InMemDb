package lab.inmemdb.repository;

public interface SequenceManager {
    Integer getDatabaseSequence();

    Integer getTableSequence();

    Integer getRecordSequence();

    Integer getAttributeSequence();
}
